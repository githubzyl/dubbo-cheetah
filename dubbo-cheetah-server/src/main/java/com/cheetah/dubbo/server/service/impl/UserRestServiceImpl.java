package com.cheetah.dubbo.server.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.cheetah.dubbo.api.common.DubboConstants;
import com.cheetah.dubbo.api.entity.User;
import com.cheetah.dubbo.api.service.IUserRestService;
import com.cheetah.dubbo.api.service.IUserService;

@Path("user")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Service(protocol= {DubboConstants.PROTOCOL_REST})
public class UserRestServiceImpl implements IUserRestService {

	@Autowired
	private IUserService userService;
	
	@GET
	@Path("{id : \\d+}")
	@Override
	public User queryById(@PathParam("id") Integer id) {
		return userService.getById(id);
	}

}
