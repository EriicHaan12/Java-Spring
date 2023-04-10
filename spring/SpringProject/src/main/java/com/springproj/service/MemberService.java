package com.springproj.service;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberVo;

public interface MemberService {
	MemberVo login(LoginDTO login) throws Exception;
}
