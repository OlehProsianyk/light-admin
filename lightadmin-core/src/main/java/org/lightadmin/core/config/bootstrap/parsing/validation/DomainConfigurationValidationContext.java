package org.lightadmin.core.config.bootstrap.parsing.validation;

import org.lightadmin.core.config.LightAdminConfiguration;
import org.lightadmin.core.config.bootstrap.parsing.DomainConfigurationProblem;
import org.lightadmin.core.config.bootstrap.parsing.InvalidPropertyConfigurationProblem;
import org.lightadmin.core.config.bootstrap.parsing.configuration.DomainConfigurationSource;
import org.lightadmin.core.config.bootstrap.parsing.configuration.DomainConfigurationUnitType;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mapping.context.MappingContext;

public class DomainConfigurationValidationContext {

    private final LightAdminConfiguration lightAdminConfiguration;

    private final DomainConfigurationSource domainConfiguration;
    private final DomainConfigurationUnitType configurationUnitType;

    private final MappingContext mappingContext;
    private final ResourceLoader resourceLoader;

    public DomainConfigurationValidationContext(LightAdminConfiguration lightAdminConfiguration, DomainConfigurationSource domainConfiguration, DomainConfigurationUnitType configurationUnitType, MappingContext mappingContext, ResourceLoader resourceLoader) {
        this.lightAdminConfiguration = lightAdminConfiguration;
        this.domainConfiguration = domainConfiguration;
        this.configurationUnitType = configurationUnitType;
        this.mappingContext = mappingContext;
        this.resourceLoader = resourceLoader;
    }

    public DomainConfigurationProblem createDomainConfigurationProblem(String message) {
        return new DomainConfigurationProblem(domainConfiguration, message);
    }

    public DomainConfigurationProblem createDomainUnitConfigurationProblem(String message) {
        return new DomainConfigurationProblem(domainConfiguration, configurationUnitType, message);
    }

    public InvalidPropertyConfigurationProblem notPersistableFieldProblem(String propertyName) {
        return InvalidPropertyConfigurationProblem.missingFieldProblem(domainConfiguration, configurationUnitType, propertyName);
    }

    public InvalidPropertyConfigurationProblem rendererNotDefinedForFieldProblem(String propertyName) {
        return InvalidPropertyConfigurationProblem.rendererNotDefinedForFieldProblem(domainConfiguration, configurationUnitType, propertyName);
    }

    public InvalidPropertyConfigurationProblem notSupportedTypeFieldProblem(String propertyName) {
        return InvalidPropertyConfigurationProblem.notSupportedTypeFieldProblem(domainConfiguration, configurationUnitType, propertyName);
    }

    public InvalidPropertyConfigurationProblem missingBaseDirectoryInFileReferenceProblem(String propertyName) {
        return InvalidPropertyConfigurationProblem.missingBaseDirectoryInFileReferenceProblem(domainConfiguration, configurationUnitType, propertyName);
    }

    public InvalidPropertyConfigurationProblem invalidPropertyValueExpressionProblem(String propertyName) {
        return InvalidPropertyConfigurationProblem.invalidPropertyValueExpressionProblem(domainConfiguration, configurationUnitType, propertyName);
    }


    public LightAdminConfiguration getLightAdminConfiguration() {
        return lightAdminConfiguration;
    }

    public MappingContext getMappingContext() {
        return mappingContext;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public DomainConfigurationSource getDomainConfiguration() {
        return domainConfiguration;
    }

    public DomainConfigurationUnitType getConfigurationUnitType() {
        return configurationUnitType;
    }
}