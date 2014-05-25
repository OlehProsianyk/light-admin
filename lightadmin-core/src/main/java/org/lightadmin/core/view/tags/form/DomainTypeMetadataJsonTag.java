package org.lightadmin.core.view.tags.form;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.lightadmin.core.config.domain.DomainTypeBasicConfiguration;
import org.lightadmin.core.config.domain.GlobalAdministrationConfiguration;
import org.lightadmin.core.config.domain.field.FieldMetadata;
import org.lightadmin.core.persistence.metamodel.PersistentPropertyType;
import org.lightadmin.core.rest.DomainTypeResourceSupport;
import org.lightadmin.core.view.tags.AbstractAutowiredTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.PersistentProperty;
import org.springframework.data.mapping.SimplePropertyHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DomainTypeMetadataJsonTag extends AbstractAutowiredTag {

    private static final JsonFactory JSON_FACTORY = new JsonFactory();

    static {
        JSON_FACTORY.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        JSON_FACTORY.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
    }

    @Autowired
    private DomainTypeResourceSupport support;

    @Autowired
    private GlobalAdministrationConfiguration globalConfiguration;

    private PersistentEntity persistentEntity;

    private Set<String> includedAttributes;

    @Override
    public void doTag() throws IOException {
        final JsonGenerator json = JSON_FACTORY.createJsonGenerator(getJspContext().getOut());
        json.writeStartObject();
        try {
            persistentEntity.doWithProperties(new SimplePropertyHandler() {
                @Override
                public void doWithPersistentProperty(PersistentProperty<?> property) {
                    if (includedAttributes != null && !includedAttributes.contains(property.getName())) {
                        return;
                    }
                    try {
                        json.writeObjectFieldStart(property.getName());

                        json.writeStringField("type", PersistentPropertyType.forPersistentProperty(property).name());
                        if (property.isAssociation()) {
                            writeAssociationMetadata(property, json);
                        }
                    } catch (Exception ex) {
                    } finally {
                        try {
                            json.writeEndObject();
                        } catch (IOException e) {
                        }
                    }
                }
            });
        } finally {
            json.writeEndObject();
            json.close();
        }
    }

    private void writeAssociationMetadata(PersistentProperty persistentProperty, JsonGenerator json) throws IOException {
        Class<?> attribDomainType = persistentProperty.getActualType();
        DomainTypeBasicConfiguration attribDomainTypeConfig = globalConfiguration.forDomainType(attribDomainType);
        if (attribDomainTypeConfig != null) {
            json.writeStringField("idAttribute", persistentEntity.getIdProperty().getName());
            String idPlaceholder = "{" + persistentProperty.getName() + "}";
            json.writeStringField("hrefTemplate", support.selfLink(attribDomainTypeConfig, idPlaceholder).getHref());
        }
    }

    public void setPersistentEntity(PersistentEntity persistentEntity) {
        this.persistentEntity = persistentEntity;
    }

    public void setIncludeFields(Collection<FieldMetadata> fields) {
        includedAttributes = new HashSet<String>();
        for (FieldMetadata field : fields) {
            includedAttributes.add(field.getUuid());
        }
    }

}
