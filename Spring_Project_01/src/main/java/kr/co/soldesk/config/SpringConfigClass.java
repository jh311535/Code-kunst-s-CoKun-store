package kr.co.soldesk.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@MapperScan(basePackages = "kr.co.soldesk.mapper")
public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {
    // DispatcherServlet에 매핑할 요청 주소를 셋팅한다.
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // Spring MVC 프로젝트 설정을 위한 클래스를 지정한다.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletAppContext.class};
    }

    // 프로젝트에서 사용할 Bean들을 정의기 위한 클래스를 지정한다.
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootAppContext.class};
    }

    // 파라미터 인코딩 필터 설정
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        return new Filter[]{encodingFilter};
    }

    // Multipart 정보 구현
    @Override
    protected void customizeRegistration(Dynamic registration) {
        super.customizeRegistration(registration);
        MultipartConfigElement config1 = new MultipartConfigElement(null, 52428800, 524288000, 0);
        registration.setMultipartConfig(config1);
    }
}