/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lightadmin.core.web.util;

import org.lightadmin.core.config.domain.DomainTypeAdministrationConfiguration;
import org.lightadmin.core.config.domain.DomainTypeBasicConfiguration;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.model.BeanWrapper;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.hateoas.Link;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

import static java.lang.String.valueOf;
import static org.lightadmin.core.web.util.WebContextUtils.getCurrentRequest;
import static org.lightadmin.core.web.util.WebContextUtils.globalAdministrationConfiguration;

public final class ApplicationUrlResolver {

    private ApplicationUrlResolver() {
    }

    public static Link selfDomainLink(PersistentEntityResource<?> resource, DomainTypeBasicConfiguration domainTypeBasicConfiguration) {
        UriComponentsBuilder selfUriBuilder = ServletUriComponentsBuilder.fromCurrentServletMapping()
                .pathSegment("domain")
                .pathSegment(domainTypeBasicConfiguration.getDomainTypeName())
                .pathSegment(idValue(resource).toString());

        return new Link(selfUriBuilder.build().toString(), "selfDomainLink");
    }

    @SuppressWarnings("unchecked")
    private static Serializable idValue(PersistentEntityResource<?> resource) {
        BeanWrapper beanWrapper = BeanWrapper.create(resource.getContent(), null);
        return (Serializable) beanWrapper.getProperty(resource.getPersistentEntity().getIdProperty());
    }

    public static String domainBaseUrl(DomainTypeAdministrationConfiguration configuration) {
        return "/domain/" + configuration.getDomainTypeName();
    }

    public static DomainTypeAdministrationConfiguration domainTypeAdministrationConfiguration(Class<?> type) {
        return globalAdministrationConfiguration(getCurrentRequest().getServletContext()).forManagedDomainType(type);
    }

    public static String domainRestBaseUrl(DomainTypeAdministrationConfiguration configuration) {
        return "/rest/" + configuration.getPluralDomainTypeName();
    }

    public static String domainRestEntityBaseUrl(DomainTypeAdministrationConfiguration configuration, Object id) {
        return domainRestBaseUrl(configuration) + "/" + (id == null ? 0 : id);
    }

    public static String domainRestScopeBaseUrl(DomainTypeAdministrationConfiguration configuration) {
        return "/rest/" + configuration.getPluralDomainTypeName() + "/scope";
    }

    public static String filePropertyRestUrl(Object entity, String property) {
        DomainTypeAdministrationConfiguration domainTypeAdministrationConfiguration = domainTypeAdministrationConfiguration(getCurrentRequest(), entity);
        PersistentEntity persistentEntity = domainTypeAdministrationConfiguration.getPersistentEntity();

        String idValue = valueOf(idAttributeValue(entity, persistentEntity));

        return domainRestEntityBaseUrl(domainTypeAdministrationConfiguration, idValue) + "/" + property + "/file";
    }

    private static DomainTypeAdministrationConfiguration domainTypeAdministrationConfiguration(HttpServletRequest currentRequest, Object entity) {
        return globalAdministrationConfiguration(currentRequest.getServletContext()).forManagedDomainType(entity.getClass());
    }

    private static Object idAttributeValue(Object entity, PersistentEntity persistentProperty) {
        return BeanWrapper.create(entity, null).getProperty(persistentProperty.getIdProperty());
    }
}