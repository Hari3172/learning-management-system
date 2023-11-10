package com.ty.lms.service;

import com.ty.lms.entity.AppUser;

public interface AppUserService {

	Boolean isUserApproved(String username);

	AppUser findUser(String username);

}
