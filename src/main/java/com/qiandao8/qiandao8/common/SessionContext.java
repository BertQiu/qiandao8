package com.qiandao8.qiandao8.common;

import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.domain.UserInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Bert Q
 * ClassName : SessionContext
 * Description 使用RequstContextHolder获取到所有request，优雅的使用session
 */
public class SessionContext {
    /**
     * 反向获取request的方法,请查看RequestContextListener.requestInitialized打包过程
     *
     * @return
     */
    private static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getSession();
    }

    public static void putCurrentUser(UserInfo current) {
        // 得到session,并把currentUser放到session中
        getSession().setAttribute(Const.CURRENT_USER.name(), current);
    }

    public static UserInfo getCurrentUser() {
        return (UserInfo) getSession().getAttribute(Const.CURRENT_USER.name());
    }

    public static void removeCurrentUser() {
        getSession().removeAttribute(Const.CURRENT_USER.name());
    }

    public static void putForgetPasswordUser(String username) {
        getSession().setAttribute(Const.RESET_PASSWORD_ACCOUNT.name(), username);
    }

    public static String getForgetPasswordUser() {
        return (String) getSession().getAttribute(Const.RESET_PASSWORD_ACCOUNT.name());
    }

    public static void removeForgetPasswordUser() {
        getSession().removeAttribute(Const.RESET_PASSWORD_ACCOUNT.name());
    }

    public static void putNearestActivity(Activity activity) {
        getSession().setAttribute(Const.NEAREST_ACTIVITY.name(), activity);
    }

    public static Activity getNearestActivity() {
        return (Activity) getSession().getAttribute(Const.NEAREST_ACTIVITY.name());
    }

    public static void removeNearestActivity() {
        getSession().removeAttribute(Const.NEAREST_ACTIVITY.name());
    }

    public static void putEnableAttendActivityPermission(Long  aid) {
        getSession().setAttribute(Const.ENABLE_ATTEND_ACTIVITY_PERMISSION.name(), aid);
    }

    public static Long getEnableAttendActivityPermission() {
        return (Long) getSession().getAttribute(Const.ENABLE_ATTEND_ACTIVITY_PERMISSION.name());
    }

    public static void removeEnableAttendActivityPermission() {
        getSession().removeAttribute(Const.ENABLE_ATTEND_ACTIVITY_PERMISSION.name());
    }


}
