package goal.jalal.goaljalal.global.configuration;

import goal.jalal.goaljalal.auth.jwt.JwtTokenProvider;
import goal.jalal.goaljalal.auth.presentation.MemberInterceptor;
import goal.jalal.goaljalal.member.configuration.MemberArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final MemberInterceptor memberInterceptor;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MemberArgumentResolver(jwtTokenProvider));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberInterceptor)
            .order(1)
            .excludePathPatterns("/api/auth/login")
            .excludePathPatterns("/api/auth/logout")
            .excludePathPatterns("/api/token/reissue")
            .addPathPatterns("/api/**");
    }
}
