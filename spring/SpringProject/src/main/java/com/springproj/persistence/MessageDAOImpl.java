package com.springproj.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springproj.domain.MessageVo;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Inject
	private SqlSession ses;

	private static String ns = "com.springproj.mappers.messageMapper";

	@Override
	public int sendMessage(MessageVo msg) throws Exception {

		return ses.insert(ns + ".sendMsg", msg);
	}

	@Override
	public List<MessageVo> selectAllMsg(String userId) throws Exception {

		return ses.selectList(ns + ".getAllMsg", userId);
	}

	@Override
	public void updateMsgReadProc(int msgId) throws Exception {

		ses.update(ns + ".updateMsgReadProc", msgId);

	}

	@Override
	public int selectMsgCnt(String userId) throws Exception {

		return ses.selectOne(ns + ".getMsgCount", userId);
	}

}
