package com.yunc.upms.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.liebao.lb7881.common.generic.GenericPageResponse;
import com.liebao.lb7881.common.utils.AssertUtils;
import com.liebao.lb7881.common.utils.ResultHandle;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.yunc.upms.common.enums.ErrorCodeEnum;
import com.yunc.upms.dao.entity.UpmsUser;
import com.yunc.upms.rpc.service.IUpmsUserService;
import com.yunc.upms.server.base.BaseController;
import com.yunc.upms.server.request.UpmsUserPageRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Controller
@RequestMapping("/upmsUser")
public class UpmsUserController extends BaseController {

	@Autowired
	private IUpmsUserService upmsUserService;

	/**
	 * 用户首页
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		return "/user/index";
	}

	/**
	 * 用户首页
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		return "/app/login";
	}

	/**
	 * 用户首页
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.GET)
	@ResponseBody
	public String doLogin(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> map) {
		// httpSession.setAttribute("user", upmsUserService.selectById(id));
		return "/app/login";
	}

	/**
	 * 新增用户
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/json/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(UpmsUser upmsUser, Map<String, Object> resMap) {
		LOGGER.info("enter create upmsUser:{}", upmsUser);
		resMap = ResultHandle.success();
		long time = System.currentTimeMillis();
		upmsUser.setCtime(time);
		upmsUserService.insert(upmsUser);
		LOGGER.info("exsit create resMap:{}", JSONUtil.toJsonStr(resMap));
		return resMap;
	}

	/**
	 * 更新获取数据
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/json/update", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> update(Long userId, Map<String, Object> resMap) {
		LOGGER.info("enter create userId:{}", userId);
		resMap = ResultHandle.success();
		resMap.put("info", upmsUserService.selectById(userId));
		LOGGER.info("exsit update resMap:{}", JSONUtil.toJsonStr(resMap));
		return resMap;
	}

	/**
	 * 更新数据
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/json/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(UpmsUser upmsUser, Map<String, Object> resMap) {
		LOGGER.info("enter update upmsUser:{}", upmsUser);
		resMap = ResultHandle.success();
		AssertUtils.isTrue(upmsUserService.updateById(upmsUser), ErrorCodeEnum.SYSTEM_ERROR);
		LOGGER.info("exsit update resMap:{}", JSONUtil.toJsonStr(resMap));
		return resMap;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param id
	 * @return [参数说明]
	 * 
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/json/list")
	@ResponseBody
	public GenericPageResponse<List<UpmsUser>> list(UpmsUserPageRequest upmsUserPageRequest,
			Map<String, Object> resMap) {
		LOGGER.info("enter list upmsUserPageRequest:{}", upmsUserPageRequest);

		resMap = ResultHandle.success();
		EntityWrapper<UpmsUser> ew = new EntityWrapper<UpmsUser>();
		ew.orderBy("ctime", false);
		UpmsUser user = new UpmsUser();
		BeanUtils.copyProperties(upmsUserPageRequest, user);
		ew.setEntity(user);
		Page<UpmsUser> page = upmsUserService.selectPage(
				new Page<UpmsUser>(upmsUserPageRequest.getPageNum(), upmsUserPageRequest.getPageSize()), ew);

		GenericPageResponse<List<UpmsUser>> n = new GenericPageResponse<List<UpmsUser>>();
		n.setTotalCount((long) page.getTotal());
		n.setObject(page.getRecords());
		LOGGER.info("exsit list n:{}", n);

		return n;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param id
	 * @return [参数说明]
	 * 
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/json/lists")
	@ResponseBody
	public List list(String id, Map<String, Object> resMap) {

		resMap = ResultHandle.success();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", 1);
		m.put("name", "lihua");
		m.put("pId", "0");
		list.add(m);
		m = new HashMap<String, Object>();
		m.put("id", 2);
		m.put("name", "lihua2");
		m.put("pId", "0");
		list.add(m);
		m = new HashMap<String, Object>();
		m.put("id", 3);
		m.put("name", "lihua3");
		m.put("pId", "1");
		list.add(m);
		return list;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param id
	 * @return [参数说明]
	 * 
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/json/login")
	@ResponseBody
	public Map<String, Object> login(String id, Map<String, Object> resMap, HttpSession httpSession,
			HttpServletRequest request, HttpServletResponse response) {

		resMap = ResultHandle.success();
		httpSession.setAttribute("user", upmsUserService.selectById(id));
		return resMap;
	}

	@RequestMapping(value = "/json/doLogin", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> doLogin(UpmsUser upmsUser, HttpServletRequest request, HttpSession httpSession) {
		LOGGER.info("enter api/doLogin upmsUser:{}", upmsUser);
		Map<String, Object> resMap = ResultHandle.success();
		String returnUrl = request.getParameter("ReturnURL");
		// 校验参数
		AssertUtils.isNotNull(upmsUser.getUsername(), ErrorCodeEnum.ILLEGAL_ARGUMENT);
		AssertUtils.isNotBlank(upmsUser.getPassword(), ErrorCodeEnum.ILLEGAL_ARGUMENT);

		Subject subject = SecurityUtils.getSubject();
		AssertUtils.isTrue(subject.isAuthenticated(), ErrorCodeEnum.LOGINED);
		UsernamePasswordToken token = new UsernamePasswordToken(upmsUser.getUsername(), upmsUser.getPassword());
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			AssertUtils.isTrue(false, ErrorCodeEnum.LOGIN_USERNAME_PWD_ERROR);
		} catch (IncorrectCredentialsException e) {
			AssertUtils.isTrue(false, ErrorCodeEnum.LOGIN_USERNAME_PWD_ERROR);
		} catch (AuthenticationException e) {
			// 其他错误，比如锁定，如果想单独处理请单独catch处理
			LOGGER.error("其他错误, login:{}", e);
			AssertUtils.isTrue(false, ErrorCodeEnum.SYSTEM_ERROR);
		}

		LOGGER.info("用户认证状态,login:{}", subject.isAuthenticated());

		returnUrl = StringUtils.isEmpty(returnUrl) ? "/user/index" : returnUrl;
		resMap.put("object", returnUrl);
		LOGGER.info("exsit api/doLogin resMap:{}", JSONUtil.toJsonStr(resMap));
		return resMap;
	}

	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

		if (httpSession.getAttribute("user") != null) {
			httpSession.removeAttribute("user");
		}
		return "redirect:/upmsUser/login";
	}

}
