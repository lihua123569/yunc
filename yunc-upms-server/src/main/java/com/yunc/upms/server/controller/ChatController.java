package com.yunc.upms.server.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.gson.GsonBuilder;
import com.yunc.upms.dao.entity.UpmsUser;
import com.yunc.upms.rpc.service.IUpmsUserService;
import com.yunc.upms.server.base.BaseController;
import com.yunc.upms.server.request.Message;
import com.yunc.upms.server.websocket.SystemWebSocketHandler;

@Controller
@RequestMapping("/chat")
public class ChatController extends BaseController {
	@Autowired
	SystemWebSocketHandler handler;
	
	@Autowired
	IUpmsUserService loginservice;
	

	@RequestMapping("/json/onlineusers")
	@ResponseBody
	public Set<String> onlineusers(HttpSession session){
		Map<Long, WebSocketSession> map=SystemWebSocketHandler.userSocketSessionMap;
		Set<Long> set=map.keySet();
		Iterator<Long> it = set.iterator();
		Set<String> nameset=new HashSet<String>();
		while(it.hasNext()){
			Long entry = it.next();
			UpmsUser name=loginservice.selectById(entry);
			Long user=(Long)session.getAttribute("uid");
			if(!user.equals(name.getUserId()))
				nameset.add(name.getUsername());
		}
		return nameset;
	}
	
	// 发布系统广播（群发）
		@ResponseBody
		@RequestMapping(value = "/json/broadcast", method = RequestMethod.POST)
		public void broadcast(@RequestParam("text") String text) throws IOException {
			Message msg = new Message();
			msg.setDate(new Date());
			msg.setFrom(-1L);//-1表示系统广播
			msg.setFromName("系统广播");
			msg.setTo(0L);
			msg.setText(text);
			handler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
		}
		
	@RequestMapping("/json/getuid")
	@ResponseBody
	public UpmsUser getuid(@RequestParam("username")String username){
		UpmsUser a=loginservice.selectById(username);
		EntityWrapper<UpmsUser> ew = new EntityWrapper<UpmsUser>();
		ew.eq("username", username);
		UpmsUser u=new UpmsUser();
		u = loginservice.selectOne(ew);
		return u;
	}
}
