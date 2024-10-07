package codegen;

///usr/bin/env jbang "$0" "$@" ; exit $?

//DEPS fr.jmini.graphql:graphql-client-generator:1.0.0-SNAPSHOT

//JAVA 17
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.jmini.gdeenco.util.CodeUtil;
import fr.jmini.gdeenco.util.FileUtil;
import fr.jmini.gql.codegen.Generator;
import fr.jmini.gql.codegen.config.Config;
import fr.jmini.gql.codegen.config.FieldsFilter;
import fr.jmini.gql.codegen.config.GraphQLClientApiAnnotation;
import fr.jmini.gql.codegen.config.IncludeStrategy;
import fr.jmini.gql.codegen.config.Scope;
import fr.jmini.gql.schema.model.IntrospectionResponse;
import fr.jmini.gql.schema.model.Schema;

class GenerateSuperheroesClient {

    public static void main(String... args) throws IOException {
        Path schemaFile = Paths.get("superheroes-graphqlschema.json");
        ObjectMapper mapper = createMapper();
        Schema schema = getSchema(mapper, schemaFile);

        Config config = createConfig(schema);

        Path sourceFolder = Paths.get("src/main/java");
        FileUtil.deleteFolder(CodeUtil.toPackageFolder(sourceFolder, config.getModelPackageName()));
        FileUtil.deleteFolder(CodeUtil.toPackageFolder(sourceFolder, config.getClientApiPackageName()));
        Generator.generateCode(sourceFolder, config);
        System.out.println("DONE");
    }

    public static Config createConfig(Schema schema) {
        Config config = new Config()
                .setSchema(schema) //
                .setScope(new Scope() //
                        .setDefaultStrategy(IncludeStrategy.INCLUDE_ALL) //
                        .addFilter(new FieldsFilter() //
                                .setTypeKind("OBJECT") //
                                .setTypeName("City") //
                                .addExcludeName("superheroes") //
                        ) //
                ) //
                .setModelPackageName("superheroes.model") //
                .setClientApiPackageName("superheroes.api") //
                .setClientApiInterfaceName("SuperheroesClientApi") //
                .setGraphQLClientApiAnnotation(new GraphQLClientApiAnnotation()
                        .setConfigKey("superheroes") //
                        .setEndpoint("http://localhost:8080/graphql") //
                );
        return config;
    }

    public static Schema getSchema(ObjectMapper mapper, Path file) {
        String originalContent = FileUtil.readFile(file);
        try {
            return mapper.readValue(originalContent, IntrospectionResponse.class)
                    .getSchema();
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Could not read schema: " + file, e);
        }
    }

    private static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        return mapper;
    }

}
