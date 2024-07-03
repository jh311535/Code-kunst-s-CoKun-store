package kr.co.soldesk.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.soldesk.beans.FindIdUserBean;
import kr.co.soldesk.beans.FindPwUserBean;
import kr.co.soldesk.beans.UserBean;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserBean.class.isAssignableFrom(clazz) || 
               FindIdUserBean.class.isAssignableFrom(clazz) || 
               FindPwUserBean.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof UserBean) {
            UserBean userBean = (UserBean) target;

            String beanName = errors.getObjectName();
            System.out.println("현재 사용 중인 beanName : " + beanName);

            if(beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {
                if(!userBean.getUser_pw().equals(userBean.getUser_pw2())) {
                    errors.rejectValue("user_pw", "NotEquals.joinUserBean.user_pw");
                    errors.rejectValue("user_pw2", "NotEquals.joinUserBean.user_pw2");
                }
            }

            if(beanName.equals("joinUserBean")) {
                if(!userBean.isUserIdExist()) {
                    errors.rejectValue("user_id", "DontCheckUserIdExist");
                }
            }
            if(beanName.equals("joinUserBean")) {
            	if(!userBean.isUserEmailExist()) {
            		errors.rejectValue("email", "DontCheckUserEmailExist");
            	}
            }
            if(beanName.equals("joinUserBean")) {
            	if(!userBean.isUserNicknameExist()) {
            		errors.rejectValue("user_nickname", "DontCheckUserNicknameExist");
            	}
            }
        } else if (target instanceof FindIdUserBean) {
            FindIdUserBean findIdUserBean = (FindIdUserBean) target;

            if (findIdUserBean.getUser_name() == null || findIdUserBean.getUser_name().isEmpty()) {
                errors.rejectValue("user_name", "NotEmpty.findIdUserBean.user_name");
            }
            if (findIdUserBean.getEmail() == null || findIdUserBean.getEmail().isEmpty()) {
                errors.rejectValue("email", "NotEmpty.findIdUserBean.email");
            }
            if (findIdUserBean.getPhone() == null || findIdUserBean.getPhone().isEmpty()) {
                errors.rejectValue("phone", "NotEmpty.findIdUserBean.phone");
            }
        } else if (target instanceof FindPwUserBean) {
            FindPwUserBean findPwUserBean = (FindPwUserBean) target;

            if (findPwUserBean.getUser_id() == null || findPwUserBean.getUser_id().isEmpty()) {
                errors.rejectValue("user_id", "NotEmpty.findPwUserBean.user_id");
            }
            if (findPwUserBean.getEmail() == null || findPwUserBean.getEmail().isEmpty()) {
                errors.rejectValue("email", "NotEmpty.findPwUserBean.email");
            }
            if (findPwUserBean.getUser_name() == null || findPwUserBean.getUser_name().isEmpty()) {
                errors.rejectValue("user_name", "NotEmpty.findPwUserBean.user_name");
            }
        }
    }
}
 