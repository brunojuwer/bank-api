package br.com.juwer.bankapi.config.security.filter;

import br.com.juwer.bankapi.api.exceptionhandler.Problem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            ((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());

            Problem problem = createProblem(HttpStatus.UNAUTHORIZED.value(), e);
            response.getWriter().write(convertProblemToJsonString(problem));
        }

    }

    private Problem createProblem(int status, Exception exeption) {
        return Problem.builder()
                .status(status)
                .detail(exeption.getMessage())
                .userMessage(exeption.getMessage())
                .build();
    }

    private String convertProblemToJsonString(Problem problem) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(problem);
    }
}
