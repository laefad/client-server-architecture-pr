package book_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import graphql.scalars.ExtendedScalars;

import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQLConfig {
   
    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        GraphQLScalarType scalarType = ExtendedScalars.Date;
        return (wiringBuilder) -> wiringBuilder.scalar(scalarType);
    }
}
