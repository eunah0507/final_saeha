package com.kh.saeha.service;

import com.kh.saeha.vo.MemberVO;

public interface MemberService {

	public void register(MemberVO vo) throws Exception;
	public MemberVO login(MemberVO vo) throws Exception;
	public void memberUpdate(MemberVO vo) throws Exception;
	public void memberDelete(MemberVO vo) throws Exception;
}
