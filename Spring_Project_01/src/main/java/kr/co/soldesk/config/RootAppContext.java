package kr.co.soldesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.soldesk.beans.BookDTO;
import kr.co.soldesk.beans.NaverDTO;
import kr.co.soldesk.beans.SidebarDTO;
import kr.co.soldesk.beans.UserBean;

@Configuration
public class RootAppContext {
	
	@Bean("loginUserBean")
	@SessionScope
	public UserBean loginUserBean() {
		return new UserBean();
	}

	@Bean("userBean")
    @SessionScope
    public UserBean userBean() {
        return new UserBean();
    }
    
    @Bean("bookDTO")
    @SessionScope
    public BookDTO bookDTO() {
        return new BookDTO();
    }
    
    @Bean("SidebarDTO")
    @SessionScope
    public SidebarDTO sidebarDTO() {
        return new SidebarDTO();
    }
	
    
    @Bean("NaverDTO")
    @SessionScope
    public NaverDTO naverDTO() {
        return new NaverDTO();
    }
	
}
