package kr.co.soldesk.config;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import interceptor.AuthInterceptor;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.interceptor.CheckLoginInterceptor;
import kr.co.soldesk.interceptor.SidebarInterceptor;
import kr.co.soldesk.interceptor.TopMenuInterceptor;
import kr.co.soldesk.mapper.BoardMapper;
import kr.co.soldesk.mapper.BookMapper;
import kr.co.soldesk.mapper.CartMapper;
import kr.co.soldesk.mapper.CommentMapper;
import kr.co.soldesk.mapper.NaverMapper;
import kr.co.soldesk.mapper.OrderMapper;
import kr.co.soldesk.mapper.ReviewMapper;
import kr.co.soldesk.mapper.SNSMapper;
import kr.co.soldesk.mapper.SidebarMapper;
import kr.co.soldesk.mapper.TopMenuMapper;
import kr.co.soldesk.mapper.UserMapper;
import kr.co.soldesk.service.SidebarService;
import kr.co.soldesk.service.TopMenuService;
import mapper.AdminInfoMapper_admin;
import mapper.BoardMapper_admin;
import mapper.BookMapper_admin;
import mapper.CartMapper_admin;
import mapper.CurationMapper_admin;
import mapper.NoticeMapper_admin;
import mapper.OrderMapper_admin;
import mapper.QnAMapper_admin;
import mapper.ReviewMapper_admin;
import mapper.UserCommentMapper_admin;
import mapper.UserInfoMapper_admin;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "kr.co.soldesk.dao", "kr.co.soldesk.service", "kr.co.soldesk.controller",
		"kr.co.soldesk.youtube", "kr.co.soldesk.mapper" })
@ComponentScan({ "controller", "interceptor" , "dao", "service"})
@ComponentScan({"kr.co.soldesk.Social"})
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {

	@Value("${db.classname}")
	private String db_classname;

	@Value("${db.url}")
	private String db_url;

	@Value("${db.username}")
	private String db_username;

	@Value("${db.password}")
	private String db_password;

	@Autowired
	private AuthInterceptor authInterceptor;

	@Autowired
	private TopMenuService topMenuService;

	@Autowired
	private SidebarService sidebarService;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	// jsp / html / css / js / jq
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	} // method

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	} // method

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		return source;
	}

	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		return factoryBean.getObject();
	}

	@Bean
	public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<>(BoardMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<TopMenuMapper> getTopMenuMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<TopMenuMapper> factoryBean = new MapperFactoryBean<>(TopMenuMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<UserMapper> getUserMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<>(UserMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<BookMapper> getBookMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<BookMapper> factoryBean = new MapperFactoryBean<>(BookMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<SidebarMapper> getSidebarMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<SidebarMapper> factoryBean = new MapperFactoryBean<SidebarMapper>(SidebarMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<NaverMapper> getNaverMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<NaverMapper> factoryBean = new MapperFactoryBean<NaverMapper>(NaverMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
	@Bean
    public MapperFactoryBean<CommentMapper> getCommentMapper(SqlSessionFactory factory) throws Exception {
    	MapperFactoryBean<CommentMapper> factoryBean = new MapperFactoryBean<>(CommentMapper.class);
    	factoryBean.setSqlSessionFactory(factory);
    	return factoryBean;
    }
	
    @Bean
    public MapperFactoryBean<ReviewMapper> getReviewMapper(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<ReviewMapper> factoryBean = new MapperFactoryBean<>(ReviewMapper.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }
    
    @Bean
    public MapperFactoryBean<SNSMapper> getSNSMapper(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<SNSMapper> factoryBean = new MapperFactoryBean<>(SNSMapper.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }

	
    
	@Bean
	public MapperFactoryBean<BookMapper_admin> getBookMapper_admin(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<BookMapper_admin> factoryBean = new MapperFactoryBean<>(BookMapper_admin.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<NoticeMapper_admin> getNoticeMapper_admin(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<NoticeMapper_admin> factoryBean = new MapperFactoryBean<>(NoticeMapper_admin.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<UserInfoMapper_admin> getUserInfoMapper_admin(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<UserInfoMapper_admin> factoryBean = new MapperFactoryBean<>(UserInfoMapper_admin.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<AdminInfoMapper_admin> getAdminInfoMapper_admin(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<AdminInfoMapper_admin> factoryBean = new MapperFactoryBean<>(AdminInfoMapper_admin.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<CurationMapper_admin> getCurationMapper_admin(SqlSessionFactory factory) throws Exception {
	    MapperFactoryBean<CurationMapper_admin> factoryBean = new MapperFactoryBean<>(CurationMapper_admin.class);
	    factoryBean.setSqlSessionFactory(factory);
	    return factoryBean;
	}

	@Bean
	public MapperFactoryBean<BoardMapper_admin> getBoardMapper_admin(SqlSessionFactory factory) throws Exception {
	    MapperFactoryBean<BoardMapper_admin> factoryBean = new MapperFactoryBean<>(BoardMapper_admin.class);
	    factoryBean.setSqlSessionFactory(factory);
	    return factoryBean;
	}
	
    @Bean
    public MapperFactoryBean<OrderMapper_admin> getOrderMapper_admin(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<OrderMapper_admin> factoryBean = new MapperFactoryBean<>(OrderMapper_admin.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }

    @Bean
    public MapperFactoryBean<CartMapper_admin> getCartMapper_admin(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<CartMapper_admin> factoryBean = new MapperFactoryBean<>(CartMapper_admin.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }
    
    @Bean
    public MapperFactoryBean<ReviewMapper_admin> getReviewMapper_admin(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<ReviewMapper_admin> factoryBean = new MapperFactoryBean<>(ReviewMapper_admin.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }
    
    @Bean
    public MapperFactoryBean<UserCommentMapper_admin> getUserCommentMapper_admin(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<UserCommentMapper_admin> factoryBean = new MapperFactoryBean<>(UserCommentMapper_admin.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }
    
    @Bean
    public MapperFactoryBean<QnAMapper_admin> getQnAMapper_admin(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<QnAMapper_admin> factoryBean = new MapperFactoryBean<>(QnAMapper_admin.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }
    
	//////////////////////////////////////////////////////////////
    
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);

		TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService, loginUserBean);
		InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
		reg1.addPathPatterns("/**");

		CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor();
		InterceptorRegistration reg2 = registry.addInterceptor(checkLoginInterceptor);

		SidebarInterceptor sidebarInterceptor = new SidebarInterceptor(sidebarService);
		InterceptorRegistration reg3 = registry.addInterceptor(sidebarInterceptor);
		reg3.addPathPatterns("/**");

		reg2.addPathPatterns("/user/modify", "/user/logout", "/board/**");
		reg2.excludePathPatterns("/board/main", "/board/home");
		
		AuthInterceptor authInterceptor = new AuthInterceptor();
		InterceptorRegistration admin_reg = registry.addInterceptor(authInterceptor);

		admin_reg.addPathPatterns("/admin/**"); // 관리자 페이지 경로 설정
		admin_reg.excludePathPatterns("/admin/login", "/admin/logout"); // 로그인/로그아웃 예외 설정

	}
	
	@Bean
	public MapperFactoryBean<CartMapper> cartMapper(SqlSessionFactory sqlSessionFactory) throws Exception {
        MapperFactoryBean<CartMapper> factoryBean = new MapperFactoryBean<>(CartMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }
	
	@Bean
	public MapperFactoryBean<OrderMapper> orderMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<OrderMapper> factoryBean = new MapperFactoryBean<OrderMapper>(OrderMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
		res.setBasenames("/WEB-INF/properties/error_message");
		return res;
	}
	
	// enctype="multipart/form-data" 사용하기 위한 클래스 객체 생성
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver(); // 객체 생성하여 반환
	}

} // class
