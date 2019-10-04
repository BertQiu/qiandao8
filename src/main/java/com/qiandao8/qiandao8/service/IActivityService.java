package com.qiandao8.qiandao8.service;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.domain.Activity;

/**
 * @author Bert Q
 * ClassName : IActivityService
 * Description TODO
 */

public interface IActivityService {
    ServerResponse createActivity(Activity activity);
}
