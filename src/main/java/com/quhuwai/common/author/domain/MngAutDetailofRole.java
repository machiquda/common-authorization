package com.quhuwai.common.author.domain;

import java.util.List;

/**
 *��ɫ����ӵ�е�Ȩ�޶�ӦDo
 * Created by fys on 2016/1/12.
 */
public class MngAutDetailofRole extends MngRoleInfoDO {

    /**
     * ��ɫ�ѷ����Ȩ���б�
     */
    private List<MngAuthorityBaseInfoDO> autList;

    public List<MngAuthorityBaseInfoDO> getAutList() {
        return autList;
    }

    public void setAutList(List<MngAuthorityBaseInfoDO> autList) {
        this.autList = autList;
    }
}
