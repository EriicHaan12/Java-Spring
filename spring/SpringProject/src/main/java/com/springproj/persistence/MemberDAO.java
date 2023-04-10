package com.springproj.persistence;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberVo;

public interface MemberDAO {
	//로그인 처리
	MemberVo selectMemberWithLoginDTO(LoginDTO login)throws Exception;
	
}
