package org.lightadmin.config;

import com.google.common.base.Predicates;
import org.lightadmin.core.annotation.Administration;
import org.lightadmin.core.config.domain.common.FieldSetConfigurationUnitBuilder;
import org.lightadmin.core.config.domain.context.ScreenContextConfigurationUnit;
import org.lightadmin.core.config.domain.context.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.core.config.domain.filter.FiltersConfigurationUnit;
import org.lightadmin.core.config.domain.filter.FiltersConfigurationUnitBuilder;
import org.lightadmin.core.config.domain.scope.ScopesConfigurationUnit;
import org.lightadmin.core.config.domain.scope.ScopesConfigurationUnitBuilder;

import org.lightadmin.core.config.domain.unit.FieldSetConfigurationUnit;
import org.lightadmin.test.model.TestCustomer;
import org.lightadmin.test.scope.DummySpecification;

import static org.lightadmin.core.config.domain.scope.ScopeMetadataUtils.all;
import static org.lightadmin.core.config.domain.scope.ScopeMetadataUtils.filter;
import static org.lightadmin.core.config.domain.scope.ScopeMetadataUtils.specification;

@SuppressWarnings( "unused" )
@Administration( TestCustomer.class )
public class CustomerTestEntityConfiguration {

	public static ScreenContextConfigurationUnit screenContext( ScreenContextConfigurationUnitBuilder screenContextBuilder ) {
		return screenContextBuilder
				.screenName( "Administration of Test Customers Administration" )
				.menuName( "Test Customers Domain" ).build();
	}

	public static FieldSetConfigurationUnit listView( final FieldSetConfigurationUnitBuilder listViewBuilder ) {
		return listViewBuilder
				.field( "firstname" ).caption( "First Name" )
				.field( "lastname" ).caption( "Last Name" )
				.field( "emailAddress" ).caption( "Email Address" ).build();
	}

	public static ScopesConfigurationUnit scopes( final ScopesConfigurationUnitBuilder scopeBuilder ) {
		return scopeBuilder
				.scope( "All", all() ).defaultScope()
				.scope( "Buyers", filter( Predicates.alwaysTrue() ) )
				.scope( "Sellers", specification( new DummySpecification() ) ).build();
	}

	public static FiltersConfigurationUnit filters( final FiltersConfigurationUnitBuilder filterBuilder ) {
		return filterBuilder
				.filter( "First Name", "firstname" )
				.filter( "Last Name", "lastname" )
				.filter( "Email Address", "emailAddress" ).build();
	}

}
