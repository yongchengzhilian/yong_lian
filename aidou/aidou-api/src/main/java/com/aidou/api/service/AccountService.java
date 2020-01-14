package com.aidou.api.service;

import com.aidou.api.vo.request.account.AccountLogoutRequest;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/9
 * Description:
 */
public interface AccountService {

    void  logout(AccountLogoutRequest accountLogoutRequest);

}
