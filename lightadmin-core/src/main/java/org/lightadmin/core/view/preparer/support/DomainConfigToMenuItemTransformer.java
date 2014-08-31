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
package org.lightadmin.core.view.preparer.support;

import org.apache.tiles.beans.MenuItem;
import org.apache.tiles.beans.SimpleMenuItem;
import org.lightadmin.core.config.domain.DomainTypeAdministrationConfiguration;
import org.lightadmin.core.util.Transformer;

import static org.lightadmin.core.web.util.ApplicationUrlResolver.domainBaseUrl;
import static org.springframework.util.StringUtils.capitalize;

public class DomainConfigToMenuItemTransformer implements Transformer<DomainTypeAdministrationConfiguration, MenuItem> {

    public static final DomainConfigToMenuItemTransformer INSTANCE = new DomainConfigToMenuItemTransformer();

    private DomainConfigToMenuItemTransformer() {
    }

    @Override
    public MenuItem apply(final DomainTypeAdministrationConfiguration domainConfiguration) {
        return menuItem(capitalize(domainConfiguration.getEntityConfiguration().getPluralName()), domainBaseUrl(domainConfiguration));
    }

    private MenuItem menuItem(final String name, final String url) {
        MenuItem menuItem = new SimpleMenuItem();
        menuItem.setValue(name);
        menuItem.setLink(url);
        return menuItem;
    }
}