
// Description: Java 11 XML SAX Element Handler for SchemaDef

/*
 *	org.msscf.msscf.CFBam
 *
 *	Copyright (c) 2020 Mark Stephen Sobkow
 *	
 *	MSS Code Factory CFBam 2.13 Business Application Model
 *	
 *	Copyright 2020-2021 Mark Stephen Sobkow
 *	
 *		This file is part of MSS Code Factory.
 *	
 *		MSS Code Factory is available under dual commercial license from Mark Stephen
 *		Sobkow, or under the terms of the GNU General Public License, Version 3
 *		or later.
 *	
 *	    MSS Code Factory is free software: you can redistribute it and/or modify
 *	    it under the terms of the GNU General Public License as published by
 *	    the Free Software Foundation, either version 3 of the License, or
 *	    (at your option) any later version.
 *	
 *	    MSS Code Factory is distributed in the hope that it will be useful,
 *	    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	    GNU General Public License for more details.
 *	
 *	    You should have received a copy of the GNU General Public License
 *	    along with MSS Code Factory.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	Donations to support MSS Code Factory can be made at
 *	https://www.paypal.com/paypalme2/MarkSobkow
 *	
 *	Please contact Mark Stephen Sobkow at mark.sobkow@gmail.com for commercial licensing.
 *
 *	Manufactured by MSS Code Factory 2.12
 */

package org.msscf.msscf.v2_13.cfbam.CFBamSaxLoader;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.xml.sax.*;
import org.msscf.msscf.v2_13.cflib.CFLib.*;
import org.msscf.msscf.v2_13.cfsec.CFSec.*;
import org.msscf.msscf.v2_13.cfint.CFInt.*;
import org.msscf.msscf.v2_13.cfbam.CFBam.*;
import org.msscf.msscf.v2_13.cfsec.CFSecObj.*;
import org.msscf.msscf.v2_13.cfint.CFIntObj.*;
import org.msscf.msscf.v2_13.cfbam.CFBamObj.*;

/*
 *	CFBamSaxLoaderSchemaDefParse XML SAX Element Handler implementation
 *	for SchemaDef.
 */
public class CFBamSaxLoaderSchemaDef
	extends CFLibXmlCoreElementHandler
{
	public CFBamSaxLoaderSchemaDef( CFBamSaxLoader saxLoader ) {
		super( saxLoader );
	}

	public void startElement(
		String		uri,
		String		localName,
		String		qName,
		Attributes	attrs )
	throws SAXException
	{
		final String S_ProcName = "startElement";
		ICFBamSchemaDefObj origBuff = null;
		ICFBamSchemaDefEditObj editBuff = null;
		// Common XML Attributes
		String attrId = null;
		// Scope Attributes
		// Scope References
		ICFBamTenantObj refTenant = null;
		// SchemaDef Attributes
		String attrName = null;
		String attrDbName = null;
		String attrShortName = null;
		String attrLabel = null;
		String attrShortDescription = null;
		String attrDescription = null;
		String attrCopyrightPeriod = null;
		String attrCopyrightHolder = null;
		String attrAuthorEMail = null;
		String attrProjectURL = null;
		String attrPublishURI = null;
		String attrCommonLicenseCode = null;
		String attrCommonLicenseName = null;
		String attrCommonLicenseText = null;
		String attrClientXFaceLicenseCode = null;
		String attrClientXFaceLicenseName = null;
		String attrClientXFaceLicenseText = null;
		String attrClientImplLicenseCode = null;
		String attrClientImplLicenseName = null;
		String attrClientImplLicenseText = null;
		String attrServerXFaceLicenseCode = null;
		String attrServerXFaceLicenseName = null;
		String attrServerXFaceLicenseText = null;
		String attrServerImplLicenseCode = null;
		String attrServerImplLicenseName = null;
		String attrServerImplLicenseText = null;
		// SchemaDef References
		ICFBamMinorVersionObj refMinorVersion = null;
		ICFBamTenantObj refCTenant = null;
		// Attribute Extraction
		String attrLocalName;
		int numAttrs;
		int idxAttr;
		final String S_LocalName = "LocalName";
		try {
			assert qName.equals( "SchemaDef" );

			CFBamSaxLoader saxLoader = (CFBamSaxLoader)getParser();
			if( saxLoader == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"getParser()" );
			}

			ICFBamSchemaObj schemaObj = saxLoader.getSchemaObj();
			if( schemaObj == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"getParser().getSchemaObj()" );
			}

			// Instantiate an edit buffer for the parsed information
			origBuff = (ICFBamSchemaDefObj)schemaObj.getSchemaDefTableObj().newInstance();
			editBuff = (ICFBamSchemaDefEditObj)origBuff.beginEdit();

			// Extract Attributes
			numAttrs = attrs.getLength();
			for( idxAttr = 0; idxAttr < numAttrs; idxAttr++ ) {
				attrLocalName = attrs.getLocalName( idxAttr );
				if( attrLocalName.equals( "Id" ) ) {
					if( attrId != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrId = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "Name" ) ) {
					if( attrName != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrName = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "DbName" ) ) {
					if( attrDbName != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrDbName = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ShortName" ) ) {
					if( attrShortName != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrShortName = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "Label" ) ) {
					if( attrLabel != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrLabel = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ShortDescription" ) ) {
					if( attrShortDescription != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrShortDescription = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "Description" ) ) {
					if( attrDescription != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrDescription = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "CopyrightPeriod" ) ) {
					if( attrCopyrightPeriod != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrCopyrightPeriod = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "CopyrightHolder" ) ) {
					if( attrCopyrightHolder != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrCopyrightHolder = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "AuthorEMail" ) ) {
					if( attrAuthorEMail != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrAuthorEMail = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ProjectURL" ) ) {
					if( attrProjectURL != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrProjectURL = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "PublishURI" ) ) {
					if( attrPublishURI != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrPublishURI = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "CommonLicenseCode" ) ) {
					if( attrCommonLicenseCode != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrCommonLicenseCode = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "CommonLicenseName" ) ) {
					if( attrCommonLicenseName != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrCommonLicenseName = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "CommonLicenseText" ) ) {
					if( attrCommonLicenseText != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrCommonLicenseText = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ClientXFaceLicenseCode" ) ) {
					if( attrClientXFaceLicenseCode != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrClientXFaceLicenseCode = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ClientXFaceLicenseName" ) ) {
					if( attrClientXFaceLicenseName != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrClientXFaceLicenseName = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ClientXFaceLicenseText" ) ) {
					if( attrClientXFaceLicenseText != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrClientXFaceLicenseText = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ClientImplLicenseCode" ) ) {
					if( attrClientImplLicenseCode != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrClientImplLicenseCode = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ClientImplLicenseName" ) ) {
					if( attrClientImplLicenseName != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrClientImplLicenseName = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ClientImplLicenseText" ) ) {
					if( attrClientImplLicenseText != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrClientImplLicenseText = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ServerXFaceLicenseCode" ) ) {
					if( attrServerXFaceLicenseCode != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrServerXFaceLicenseCode = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ServerXFaceLicenseName" ) ) {
					if( attrServerXFaceLicenseName != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrServerXFaceLicenseName = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ServerXFaceLicenseText" ) ) {
					if( attrServerXFaceLicenseText != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrServerXFaceLicenseText = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ServerImplLicenseCode" ) ) {
					if( attrServerImplLicenseCode != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrServerImplLicenseCode = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ServerImplLicenseName" ) ) {
					if( attrServerImplLicenseName != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrServerImplLicenseName = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "ServerImplLicenseText" ) ) {
					if( attrServerImplLicenseText != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrServerImplLicenseText = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "schemaLocation" ) ) {
					// ignored
				}
				else {
					throw new CFLibUnrecognizedAttributeException( getClass(),
						S_ProcName,
						getParser().getLocationInfo(),
						attrLocalName );
				}
			}

			// Ensure that required attributes have values
			if( attrName == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"Name" );
			}
			if( attrCopyrightPeriod == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"CopyrightPeriod" );
			}
			if( attrCopyrightHolder == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"CopyrightHolder" );
			}
			if( attrAuthorEMail == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"AuthorEMail" );
			}
			if( attrProjectURL == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"ProjectURL" );
			}
			if( attrPublishURI == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"PublishURI" );
			}
			if( attrCommonLicenseCode == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"CommonLicenseCode" );
			}
			if( attrCommonLicenseName == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"CommonLicenseName" );
			}
			if( attrCommonLicenseText == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"CommonLicenseText" );
			}

			// Save named attributes to context
			CFLibXmlCoreContext curContext = getParser().getCurContext();
			curContext.putNamedValue( "Id", attrId );
			curContext.putNamedValue( "Name", attrName );
			curContext.putNamedValue( "DbName", attrDbName );
			curContext.putNamedValue( "ShortName", attrShortName );
			curContext.putNamedValue( "Label", attrLabel );
			curContext.putNamedValue( "ShortDescription", attrShortDescription );
			curContext.putNamedValue( "Description", attrDescription );
			curContext.putNamedValue( "CopyrightPeriod", attrCopyrightPeriod );
			curContext.putNamedValue( "CopyrightHolder", attrCopyrightHolder );
			curContext.putNamedValue( "AuthorEMail", attrAuthorEMail );
			curContext.putNamedValue( "ProjectURL", attrProjectURL );
			curContext.putNamedValue( "PublishURI", attrPublishURI );
			curContext.putNamedValue( "CommonLicenseCode", attrCommonLicenseCode );
			curContext.putNamedValue( "CommonLicenseName", attrCommonLicenseName );
			curContext.putNamedValue( "CommonLicenseText", attrCommonLicenseText );
			curContext.putNamedValue( "ClientXFaceLicenseCode", attrClientXFaceLicenseCode );
			curContext.putNamedValue( "ClientXFaceLicenseName", attrClientXFaceLicenseName );
			curContext.putNamedValue( "ClientXFaceLicenseText", attrClientXFaceLicenseText );
			curContext.putNamedValue( "ClientImplLicenseCode", attrClientImplLicenseCode );
			curContext.putNamedValue( "ClientImplLicenseName", attrClientImplLicenseName );
			curContext.putNamedValue( "ClientImplLicenseText", attrClientImplLicenseText );
			curContext.putNamedValue( "ServerXFaceLicenseCode", attrServerXFaceLicenseCode );
			curContext.putNamedValue( "ServerXFaceLicenseName", attrServerXFaceLicenseName );
			curContext.putNamedValue( "ServerXFaceLicenseText", attrServerXFaceLicenseText );
			curContext.putNamedValue( "ServerImplLicenseCode", attrServerImplLicenseCode );
			curContext.putNamedValue( "ServerImplLicenseName", attrServerImplLicenseName );
			curContext.putNamedValue( "ServerImplLicenseText", attrServerImplLicenseText );

			// Convert string attributes to native Java types
			// and apply the converted attributes to the editBuff.

			Integer natId;
			if( ( attrId != null ) && ( attrId.length() > 0 ) ) {
				natId = Integer.valueOf( Integer.parseInt( attrId ) );
			}
			else {
				natId = null;
			}
			String natName = attrName;
			editBuff.setRequiredName( natName );

			String natDbName = attrDbName;
			editBuff.setOptionalDbName( natDbName );

			String natShortName = attrShortName;
			editBuff.setOptionalShortName( natShortName );

			String natLabel = attrLabel;
			editBuff.setOptionalLabel( natLabel );

			String natShortDescription = attrShortDescription;
			editBuff.setOptionalShortDescription( natShortDescription );

			String natDescription = attrDescription;
			editBuff.setOptionalDescription( natDescription );

			String natCopyrightPeriod = attrCopyrightPeriod;
			editBuff.setRequiredCopyrightPeriod( natCopyrightPeriod );

			String natCopyrightHolder = attrCopyrightHolder;
			editBuff.setRequiredCopyrightHolder( natCopyrightHolder );

			String natAuthorEMail = attrAuthorEMail;
			editBuff.setRequiredAuthorEMail( natAuthorEMail );

			String natProjectURL = attrProjectURL;
			editBuff.setRequiredProjectURL( natProjectURL );

			String natPublishURI = attrPublishURI;
			editBuff.setRequiredPublishURI( natPublishURI );

			String natCommonLicenseCode = attrCommonLicenseCode;
			editBuff.setRequiredCommonLicenseCode( natCommonLicenseCode );

			String natCommonLicenseName = attrCommonLicenseName;
			editBuff.setRequiredCommonLicenseName( natCommonLicenseName );

			String natCommonLicenseText = attrCommonLicenseText;
			editBuff.setRequiredCommonLicenseText( natCommonLicenseText );

			String natClientXFaceLicenseCode = attrClientXFaceLicenseCode;
			editBuff.setOptionalClientXFaceLicenseCode( natClientXFaceLicenseCode );

			String natClientXFaceLicenseName = attrClientXFaceLicenseName;
			editBuff.setOptionalClientXFaceLicenseName( natClientXFaceLicenseName );

			String natClientXFaceLicenseText = attrClientXFaceLicenseText;
			editBuff.setOptionalClientXFaceLicenseText( natClientXFaceLicenseText );

			String natClientImplLicenseCode = attrClientImplLicenseCode;
			editBuff.setOptionalClientImplLicenseCode( natClientImplLicenseCode );

			String natClientImplLicenseName = attrClientImplLicenseName;
			editBuff.setOptionalClientImplLicenseName( natClientImplLicenseName );

			String natClientImplLicenseText = attrClientImplLicenseText;
			editBuff.setOptionalClientImplLicenseText( natClientImplLicenseText );

			String natServerXFaceLicenseCode = attrServerXFaceLicenseCode;
			editBuff.setOptionalServerXFaceLicenseCode( natServerXFaceLicenseCode );

			String natServerXFaceLicenseName = attrServerXFaceLicenseName;
			editBuff.setOptionalServerXFaceLicenseName( natServerXFaceLicenseName );

			String natServerXFaceLicenseText = attrServerXFaceLicenseText;
			editBuff.setOptionalServerXFaceLicenseText( natServerXFaceLicenseText );

			String natServerImplLicenseCode = attrServerImplLicenseCode;
			editBuff.setOptionalServerImplLicenseCode( natServerImplLicenseCode );

			String natServerImplLicenseName = attrServerImplLicenseName;
			editBuff.setOptionalServerImplLicenseName( natServerImplLicenseName );

			String natServerImplLicenseText = attrServerImplLicenseText;
			editBuff.setOptionalServerImplLicenseText( natServerImplLicenseText );

			// Get the scope/container object

			CFLibXmlCoreContext parentContext = curContext.getPrevContext();
			Object scopeObj;
			if( parentContext != null ) {
				scopeObj = parentContext.getNamedValue( "Object" );
			}
			else {
				scopeObj = null;
			}

			// Resolve and apply required Container reference

			if( scopeObj == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"scopeObj" );
			}
			else if( scopeObj instanceof ICFBamMinorVersionObj ) {
				refMinorVersion = (ICFBamMinorVersionObj) scopeObj;
				editBuff.setRequiredContainerMinorVersion( refMinorVersion );
				refCTenant = (ICFBamTenantObj)editBuff.getRequiredOwnerCTenant();
			}
			else {
				throw new CFLibUnsupportedClassException( getClass(),
					S_ProcName,
					"scopeObj",
					scopeObj,
					"ICFBamMinorVersionObj" );
			}

			// Resolve and apply Owner reference

			if( refCTenant == null ) {
				if( scopeObj instanceof ICFBamTenantObj ) {
					refCTenant = (ICFBamTenantObj) scopeObj;
					editBuff.setRequiredOwnerCTenant( refCTenant );
				}
				else {
					throw new CFLibNullArgumentException( getClass(),
						S_ProcName,
						0,
						"Owner<CTenant>" );
				}
			}

			refTenant = refCTenant;
			CFBamSaxLoader.LoaderBehaviourEnum loaderBehaviour = saxLoader.getSchemaDefLoaderBehaviour();
			ICFBamSchemaDefEditObj editSchemaDef = null;
			ICFBamSchemaDefObj origSchemaDef = (ICFBamSchemaDefObj)schemaObj.getSchemaDefTableObj().readSchemaDefByUNameIdx( refMinorVersion.getRequiredTenantId(),
			refMinorVersion.getRequiredId(),
			editBuff.getRequiredName() );
			if( origSchemaDef == null ) {
				editSchemaDef = editBuff;
			}
			else {
				switch( loaderBehaviour ) {
					case Insert:
						break;
					case Update:
						editSchemaDef = (ICFBamSchemaDefEditObj)origSchemaDef.beginEdit();
						editSchemaDef.setRequiredName( editBuff.getRequiredName() );
						editSchemaDef.setOptionalDbName( editBuff.getOptionalDbName() );
						editSchemaDef.setOptionalShortName( editBuff.getOptionalShortName() );
						editSchemaDef.setOptionalLabel( editBuff.getOptionalLabel() );
						editSchemaDef.setOptionalShortDescription( editBuff.getOptionalShortDescription() );
						editSchemaDef.setOptionalDescription( editBuff.getOptionalDescription() );
						editSchemaDef.setRequiredCopyrightPeriod( editBuff.getRequiredCopyrightPeriod() );
						editSchemaDef.setRequiredCopyrightHolder( editBuff.getRequiredCopyrightHolder() );
						editSchemaDef.setRequiredAuthorEMail( editBuff.getRequiredAuthorEMail() );
						editSchemaDef.setRequiredProjectURL( editBuff.getRequiredProjectURL() );
						editSchemaDef.setRequiredPublishURI( editBuff.getRequiredPublishURI() );
						editSchemaDef.setRequiredCommonLicenseCode( editBuff.getRequiredCommonLicenseCode() );
						editSchemaDef.setRequiredCommonLicenseName( editBuff.getRequiredCommonLicenseName() );
						editSchemaDef.setRequiredCommonLicenseText( editBuff.getRequiredCommonLicenseText() );
						editSchemaDef.setOptionalClientXFaceLicenseCode( editBuff.getOptionalClientXFaceLicenseCode() );
						editSchemaDef.setOptionalClientXFaceLicenseName( editBuff.getOptionalClientXFaceLicenseName() );
						editSchemaDef.setOptionalClientXFaceLicenseText( editBuff.getOptionalClientXFaceLicenseText() );
						editSchemaDef.setOptionalClientImplLicenseCode( editBuff.getOptionalClientImplLicenseCode() );
						editSchemaDef.setOptionalClientImplLicenseName( editBuff.getOptionalClientImplLicenseName() );
						editSchemaDef.setOptionalClientImplLicenseText( editBuff.getOptionalClientImplLicenseText() );
						editSchemaDef.setOptionalServerXFaceLicenseCode( editBuff.getOptionalServerXFaceLicenseCode() );
						editSchemaDef.setOptionalServerXFaceLicenseName( editBuff.getOptionalServerXFaceLicenseName() );
						editSchemaDef.setOptionalServerXFaceLicenseText( editBuff.getOptionalServerXFaceLicenseText() );
						editSchemaDef.setOptionalServerImplLicenseCode( editBuff.getOptionalServerImplLicenseCode() );
						editSchemaDef.setOptionalServerImplLicenseName( editBuff.getOptionalServerImplLicenseName() );
						editSchemaDef.setOptionalServerImplLicenseText( editBuff.getOptionalServerImplLicenseText() );
						editSchemaDef.setOptionalJSchemaObjImport( editBuff.getOptionalJSchemaObjImport() );
						editSchemaDef.setOptionalJSchemaObjInterface( editBuff.getOptionalJSchemaObjInterface() );
						editSchemaDef.setOptionalJSchemaObjMembers( editBuff.getOptionalJSchemaObjMembers() );
						editSchemaDef.setOptionalJSchemaObjImplementation( editBuff.getOptionalJSchemaObjImplementation() );
						editSchemaDef.setOptionalJDb2LUWSchemaObjMembers( editBuff.getOptionalJDb2LUWSchemaObjMembers() );
						editSchemaDef.setOptionalJDb2LUWSchemaObjImpl( editBuff.getOptionalJDb2LUWSchemaObjImpl() );
						editSchemaDef.setOptionalJDb2LUWSchemaObjImport( editBuff.getOptionalJDb2LUWSchemaObjImport() );
						editSchemaDef.setOptionalJMSSqlSchemaObjMembers( editBuff.getOptionalJMSSqlSchemaObjMembers() );
						editSchemaDef.setOptionalJMSSqlSchemaObjImpl( editBuff.getOptionalJMSSqlSchemaObjImpl() );
						editSchemaDef.setOptionalJMSSqlSchemaObjImport( editBuff.getOptionalJMSSqlSchemaObjImport() );
						editSchemaDef.setOptionalJMySqlSchemaObjMembers( editBuff.getOptionalJMySqlSchemaObjMembers() );
						editSchemaDef.setOptionalJMySqlSchemaObjImpl( editBuff.getOptionalJMySqlSchemaObjImpl() );
						editSchemaDef.setOptionalJMySqlSchemaObjImport( editBuff.getOptionalJMySqlSchemaObjImport() );
						editSchemaDef.setOptionalJOracleSchemaObjMembers( editBuff.getOptionalJOracleSchemaObjMembers() );
						editSchemaDef.setOptionalJOracleSchemaObjImpl( editBuff.getOptionalJOracleSchemaObjImpl() );
						editSchemaDef.setOptionalJOracleSchemaObjImport( editBuff.getOptionalJOracleSchemaObjImport() );
						editSchemaDef.setOptionalJPgSqlSchemaObjMembers( editBuff.getOptionalJPgSqlSchemaObjMembers() );
						editSchemaDef.setOptionalJPgSqlSchemaObjImpl( editBuff.getOptionalJPgSqlSchemaObjImpl() );
						editSchemaDef.setOptionalJPgSqlSchemaObjImport( editBuff.getOptionalJPgSqlSchemaObjImport() );
						editSchemaDef.setOptionalJRamSchemaObjMembers( editBuff.getOptionalJRamSchemaObjMembers() );
						editSchemaDef.setOptionalJRamSchemaObjImpl( editBuff.getOptionalJRamSchemaObjImpl() );
						editSchemaDef.setOptionalJRamSchemaObjImport( editBuff.getOptionalJRamSchemaObjImport() );
						editSchemaDef.setOptionalJXMsgSchemaImport( editBuff.getOptionalJXMsgSchemaImport() );
						editSchemaDef.setOptionalJXMsgSchemaFormatters( editBuff.getOptionalJXMsgSchemaFormatters() );
						editSchemaDef.setOptionalJXMsgClientSchemaImport( editBuff.getOptionalJXMsgClientSchemaImport() );
						editSchemaDef.setOptionalJXMsgClientSchemaBody( editBuff.getOptionalJXMsgClientSchemaBody() );
						editSchemaDef.setOptionalJXMsgRqstSchemaBody( editBuff.getOptionalJXMsgRqstSchemaBody() );
						editSchemaDef.setOptionalJXMsgRqstSchemaImport( editBuff.getOptionalJXMsgRqstSchemaImport() );
						editSchemaDef.setOptionalJXMsgRqstSchemaWireParsers( editBuff.getOptionalJXMsgRqstSchemaWireParsers() );
						editSchemaDef.setOptionalJXMsgRqstSchemaXsdSpec( editBuff.getOptionalJXMsgRqstSchemaXsdSpec() );
						editSchemaDef.setOptionalJXMsgRqstSchemaXsdElementList( editBuff.getOptionalJXMsgRqstSchemaXsdElementList() );
						editSchemaDef.setOptionalJXMsgRspnSchemaBody( editBuff.getOptionalJXMsgRspnSchemaBody() );
						editSchemaDef.setOptionalJXMsgRspnSchemaImport( editBuff.getOptionalJXMsgRspnSchemaImport() );
						editSchemaDef.setOptionalJXMsgRspnSchemaWireParsers( editBuff.getOptionalJXMsgRspnSchemaWireParsers() );
						editSchemaDef.setOptionalJXMsgRspnSchemaXsdElementList( editBuff.getOptionalJXMsgRspnSchemaXsdElementList() );
						editSchemaDef.setOptionalJXMsgRspnSchemaXsdSpec( editBuff.getOptionalJXMsgRspnSchemaXsdSpec() );
						editSchemaDef.setOptionalCppSchemaObjInclude( editBuff.getOptionalCppSchemaObjInclude() );
						editSchemaDef.setOptionalCppSchemaObjInterface( editBuff.getOptionalCppSchemaObjInterface() );
						editSchemaDef.setOptionalCppSchemaObjMembers( editBuff.getOptionalCppSchemaObjMembers() );
						editSchemaDef.setOptionalCppSchemaObjImplementation( editBuff.getOptionalCppSchemaObjImplementation() );
						editSchemaDef.setOptionalCppDb2LUWSchemaObjMembers( editBuff.getOptionalCppDb2LUWSchemaObjMembers() );
						editSchemaDef.setOptionalCppDb2LUWSchemaObjImpl( editBuff.getOptionalCppDb2LUWSchemaObjImpl() );
						editSchemaDef.setOptionalCppDb2LUWSchemaObjInclude( editBuff.getOptionalCppDb2LUWSchemaObjInclude() );
						editSchemaDef.setOptionalCppMSSqlSchemaObjMembers( editBuff.getOptionalCppMSSqlSchemaObjMembers() );
						editSchemaDef.setOptionalCppMSSqlSchemaObjImpl( editBuff.getOptionalCppMSSqlSchemaObjImpl() );
						editSchemaDef.setOptionalCppMSSqlSchemaObjInclude( editBuff.getOptionalCppMSSqlSchemaObjInclude() );
						editSchemaDef.setOptionalCppMySqlSchemaObjMembers( editBuff.getOptionalCppMySqlSchemaObjMembers() );
						editSchemaDef.setOptionalCppMySqlSchemaObjImpl( editBuff.getOptionalCppMySqlSchemaObjImpl() );
						editSchemaDef.setOptionalCppMySqlSchemaObjInclude( editBuff.getOptionalCppMySqlSchemaObjInclude() );
						editSchemaDef.setOptionalCppOracleSchemaObjMembers( editBuff.getOptionalCppOracleSchemaObjMembers() );
						editSchemaDef.setOptionalCppOracleSchemaObjImpl( editBuff.getOptionalCppOracleSchemaObjImpl() );
						editSchemaDef.setOptionalCppOracleSchemaObjInclude( editBuff.getOptionalCppOracleSchemaObjInclude() );
						editSchemaDef.setOptionalCppPgSqlSchemaObjMembers( editBuff.getOptionalCppPgSqlSchemaObjMembers() );
						editSchemaDef.setOptionalCppPgSqlSchemaObjImpl( editBuff.getOptionalCppPgSqlSchemaObjImpl() );
						editSchemaDef.setOptionalCppPgSqlSchemaObjInclude( editBuff.getOptionalCppPgSqlSchemaObjInclude() );
						editSchemaDef.setOptionalCppRamSchemaObjMembers( editBuff.getOptionalCppRamSchemaObjMembers() );
						editSchemaDef.setOptionalCppRamSchemaObjImpl( editBuff.getOptionalCppRamSchemaObjImpl() );
						editSchemaDef.setOptionalCppRamSchemaObjInclude( editBuff.getOptionalCppRamSchemaObjInclude() );
						editSchemaDef.setOptionalCppXMsgSchemaInclude( editBuff.getOptionalCppXMsgSchemaInclude() );
						editSchemaDef.setOptionalCppXMsgSchemaFormatters( editBuff.getOptionalCppXMsgSchemaFormatters() );
						editSchemaDef.setOptionalCppXMsgClientSchemaInclude( editBuff.getOptionalCppXMsgClientSchemaInclude() );
						editSchemaDef.setOptionalCppXMsgClientSchemaBody( editBuff.getOptionalCppXMsgClientSchemaBody() );
						editSchemaDef.setOptionalCppXMsgRqstSchemaBody( editBuff.getOptionalCppXMsgRqstSchemaBody() );
						editSchemaDef.setOptionalCppXMsgRqstSchemaInclude( editBuff.getOptionalCppXMsgRqstSchemaInclude() );
						editSchemaDef.setOptionalCppXMsgRqstSchemaWireParsers( editBuff.getOptionalCppXMsgRqstSchemaWireParsers() );
						editSchemaDef.setOptionalCppXMsgRqstSchemaXsdSpec( editBuff.getOptionalCppXMsgRqstSchemaXsdSpec() );
						editSchemaDef.setOptionalCppXMsgRqstSchemaXsdElementList( editBuff.getOptionalCppXMsgRqstSchemaXsdElementList() );
						editSchemaDef.setOptionalCppXMsgRspnSchemaBody( editBuff.getOptionalCppXMsgRspnSchemaBody() );
						editSchemaDef.setOptionalCppXMsgRspnSchemaInclude( editBuff.getOptionalCppXMsgRspnSchemaInclude() );
						editSchemaDef.setOptionalCppXMsgRspnSchemaWireParsers( editBuff.getOptionalCppXMsgRspnSchemaWireParsers() );
						editSchemaDef.setOptionalCppXMsgRspnSchemaXsdElementList( editBuff.getOptionalCppXMsgRspnSchemaXsdElementList() );
						editSchemaDef.setOptionalCppXMsgRspnSchemaXsdSpec( editBuff.getOptionalCppXMsgRspnSchemaXsdSpec() );
						editSchemaDef.setOptionalHppSchemaObjInclude( editBuff.getOptionalHppSchemaObjInclude() );
						editSchemaDef.setOptionalHppSchemaObjInterface( editBuff.getOptionalHppSchemaObjInterface() );
						editSchemaDef.setOptionalHppSchemaObjMembers( editBuff.getOptionalHppSchemaObjMembers() );
						editSchemaDef.setOptionalHppSchemaObjImplementation( editBuff.getOptionalHppSchemaObjImplementation() );
						editSchemaDef.setOptionalHppDb2LUWSchemaObjMembers( editBuff.getOptionalHppDb2LUWSchemaObjMembers() );
						editSchemaDef.setOptionalHppDb2LUWSchemaObjImpl( editBuff.getOptionalHppDb2LUWSchemaObjImpl() );
						editSchemaDef.setOptionalHppDb2LUWSchemaObjInclude( editBuff.getOptionalHppDb2LUWSchemaObjInclude() );
						editSchemaDef.setOptionalHppMSSqlSchemaObjMembers( editBuff.getOptionalHppMSSqlSchemaObjMembers() );
						editSchemaDef.setOptionalHppMSSqlSchemaObjImpl( editBuff.getOptionalHppMSSqlSchemaObjImpl() );
						editSchemaDef.setOptionalHppMSSqlSchemaObjInclude( editBuff.getOptionalHppMSSqlSchemaObjInclude() );
						editSchemaDef.setOptionalHppMySqlSchemaObjMembers( editBuff.getOptionalHppMySqlSchemaObjMembers() );
						editSchemaDef.setOptionalHppMySqlSchemaObjImpl( editBuff.getOptionalHppMySqlSchemaObjImpl() );
						editSchemaDef.setOptionalHppMySqlSchemaObjInclude( editBuff.getOptionalHppMySqlSchemaObjInclude() );
						editSchemaDef.setOptionalHppOracleSchemaObjMembers( editBuff.getOptionalHppOracleSchemaObjMembers() );
						editSchemaDef.setOptionalHppOracleSchemaObjImpl( editBuff.getOptionalHppOracleSchemaObjImpl() );
						editSchemaDef.setOptionalHppOracleSchemaObjInclude( editBuff.getOptionalHppOracleSchemaObjInclude() );
						editSchemaDef.setOptionalHppPgSqlSchemaObjMembers( editBuff.getOptionalHppPgSqlSchemaObjMembers() );
						editSchemaDef.setOptionalHppPgSqlSchemaObjImpl( editBuff.getOptionalHppPgSqlSchemaObjImpl() );
						editSchemaDef.setOptionalHppPgSqlSchemaObjInclude( editBuff.getOptionalHppPgSqlSchemaObjInclude() );
						editSchemaDef.setOptionalHppRamSchemaObjMembers( editBuff.getOptionalHppRamSchemaObjMembers() );
						editSchemaDef.setOptionalHppRamSchemaObjImpl( editBuff.getOptionalHppRamSchemaObjImpl() );
						editSchemaDef.setOptionalHppRamSchemaObjInclude( editBuff.getOptionalHppRamSchemaObjInclude() );
						editSchemaDef.setOptionalHppXMsgSchemaInclude( editBuff.getOptionalHppXMsgSchemaInclude() );
						editSchemaDef.setOptionalHppXMsgSchemaFormatters( editBuff.getOptionalHppXMsgSchemaFormatters() );
						editSchemaDef.setOptionalHppXMsgClientSchemaInclude( editBuff.getOptionalHppXMsgClientSchemaInclude() );
						editSchemaDef.setOptionalHppXMsgClientSchemaBody( editBuff.getOptionalHppXMsgClientSchemaBody() );
						editSchemaDef.setOptionalHppXMsgRqstSchemaBody( editBuff.getOptionalHppXMsgRqstSchemaBody() );
						editSchemaDef.setOptionalHppXMsgRqstSchemaInclude( editBuff.getOptionalHppXMsgRqstSchemaInclude() );
						editSchemaDef.setOptionalHppXMsgRqstSchemaWireParsers( editBuff.getOptionalHppXMsgRqstSchemaWireParsers() );
						editSchemaDef.setOptionalHppXMsgRqstSchemaXsdSpec( editBuff.getOptionalHppXMsgRqstSchemaXsdSpec() );
						editSchemaDef.setOptionalHppXMsgRqstSchemaXsdElementList( editBuff.getOptionalHppXMsgRqstSchemaXsdElementList() );
						editSchemaDef.setOptionalHppXMsgRspnSchemaBody( editBuff.getOptionalHppXMsgRspnSchemaBody() );
						editSchemaDef.setOptionalHppXMsgRspnSchemaInclude( editBuff.getOptionalHppXMsgRspnSchemaInclude() );
						editSchemaDef.setOptionalHppXMsgRspnSchemaWireParsers( editBuff.getOptionalHppXMsgRspnSchemaWireParsers() );
						editSchemaDef.setOptionalHppXMsgRspnSchemaXsdElementList( editBuff.getOptionalHppXMsgRspnSchemaXsdElementList() );
						editSchemaDef.setOptionalHppXMsgRspnSchemaXsdSpec( editBuff.getOptionalHppXMsgRspnSchemaXsdSpec() );
						editSchemaDef.setOptionalCsSchemaObjUsing( editBuff.getOptionalCsSchemaObjUsing() );
						editSchemaDef.setOptionalCsSchemaObjInterface( editBuff.getOptionalCsSchemaObjInterface() );
						editSchemaDef.setOptionalCsSchemaObjMembers( editBuff.getOptionalCsSchemaObjMembers() );
						editSchemaDef.setOptionalCsSchemaObjImplementation( editBuff.getOptionalCsSchemaObjImplementation() );
						editSchemaDef.setOptionalCsDb2LUWSchemaObjMembers( editBuff.getOptionalCsDb2LUWSchemaObjMembers() );
						editSchemaDef.setOptionalCsDb2LUWSchemaObjImpl( editBuff.getOptionalCsDb2LUWSchemaObjImpl() );
						editSchemaDef.setOptionalCsDb2LUWSchemaObjUsing( editBuff.getOptionalCsDb2LUWSchemaObjUsing() );
						editSchemaDef.setOptionalCsMSSqlSchemaObjMembers( editBuff.getOptionalCsMSSqlSchemaObjMembers() );
						editSchemaDef.setOptionalCsMSSqlSchemaObjImpl( editBuff.getOptionalCsMSSqlSchemaObjImpl() );
						editSchemaDef.setOptionalCsMSSqlSchemaObjUsing( editBuff.getOptionalCsMSSqlSchemaObjUsing() );
						editSchemaDef.setOptionalCsMySqlSchemaObjMembers( editBuff.getOptionalCsMySqlSchemaObjMembers() );
						editSchemaDef.setOptionalCsMySqlSchemaObjImpl( editBuff.getOptionalCsMySqlSchemaObjImpl() );
						editSchemaDef.setOptionalCsMySqlSchemaObjUsing( editBuff.getOptionalCsMySqlSchemaObjUsing() );
						editSchemaDef.setOptionalCsOracleSchemaObjMembers( editBuff.getOptionalCsOracleSchemaObjMembers() );
						editSchemaDef.setOptionalCsOracleSchemaObjImpl( editBuff.getOptionalCsOracleSchemaObjImpl() );
						editSchemaDef.setOptionalCsOracleSchemaObjUsing( editBuff.getOptionalCsOracleSchemaObjUsing() );
						editSchemaDef.setOptionalCsPgSqlSchemaObjMembers( editBuff.getOptionalCsPgSqlSchemaObjMembers() );
						editSchemaDef.setOptionalCsPgSqlSchemaObjImpl( editBuff.getOptionalCsPgSqlSchemaObjImpl() );
						editSchemaDef.setOptionalCsPgSqlSchemaObjUsing( editBuff.getOptionalCsPgSqlSchemaObjUsing() );
						editSchemaDef.setOptionalCsRamSchemaObjMembers( editBuff.getOptionalCsRamSchemaObjMembers() );
						editSchemaDef.setOptionalCsRamSchemaObjImpl( editBuff.getOptionalCsRamSchemaObjImpl() );
						editSchemaDef.setOptionalCsRamSchemaObjUsing( editBuff.getOptionalCsRamSchemaObjUsing() );
						editSchemaDef.setOptionalCsXMsgSchemaUsing( editBuff.getOptionalCsXMsgSchemaUsing() );
						editSchemaDef.setOptionalCsXMsgSchemaFormatters( editBuff.getOptionalCsXMsgSchemaFormatters() );
						editSchemaDef.setOptionalCsXMsgClientSchemaUsing( editBuff.getOptionalCsXMsgClientSchemaUsing() );
						editSchemaDef.setOptionalCsXMsgClientSchemaBody( editBuff.getOptionalCsXMsgClientSchemaBody() );
						editSchemaDef.setOptionalCsXMsgRqstSchemaBody( editBuff.getOptionalCsXMsgRqstSchemaBody() );
						editSchemaDef.setOptionalCsXMsgRqstSchemaUsing( editBuff.getOptionalCsXMsgRqstSchemaUsing() );
						editSchemaDef.setOptionalCsXMsgRqstSchemaWireParsers( editBuff.getOptionalCsXMsgRqstSchemaWireParsers() );
						editSchemaDef.setOptionalCsXMsgRqstSchemaXsdSpec( editBuff.getOptionalCsXMsgRqstSchemaXsdSpec() );
						editSchemaDef.setOptionalCsXMsgRqstSchemaXsdElementList( editBuff.getOptionalCsXMsgRqstSchemaXsdElementList() );
						editSchemaDef.setOptionalCsXMsgRspnSchemaBody( editBuff.getOptionalCsXMsgRspnSchemaBody() );
						editSchemaDef.setOptionalCsXMsgRspnSchemaUsing( editBuff.getOptionalCsXMsgRspnSchemaUsing() );
						editSchemaDef.setOptionalCsXMsgRspnSchemaWireParsers( editBuff.getOptionalCsXMsgRspnSchemaWireParsers() );
						editSchemaDef.setOptionalCsXMsgRspnSchemaXsdElementList( editBuff.getOptionalCsXMsgRspnSchemaXsdElementList() );
						editSchemaDef.setOptionalCsXMsgRspnSchemaXsdSpec( editBuff.getOptionalCsXMsgRspnSchemaXsdSpec() );
						break;
					case Replace:
						editSchemaDef = (ICFBamSchemaDefEditObj)origSchemaDef.beginEdit();
						editSchemaDef.deleteInstance();
						editSchemaDef = null;
						origSchemaDef = null;
						editSchemaDef = editBuff;
						break;
				}
			}

			if( editSchemaDef != null ) {
				if( origSchemaDef != null ) {
					editSchemaDef.update();
				}
				else {
					origSchemaDef = (ICFBamSchemaDefObj)editSchemaDef.create();
				}
				editSchemaDef = null;
			}

			curContext.putNamedValue( "Object", origSchemaDef );
		}
		catch( RuntimeException e ) {
			throw new SAXException( "Near " + getParser().getLocationInfo() + ": Caught and rethrew " + e.getClass().getName() + " - " + e.getMessage(),
				e );
		}
		catch( Error e ) {
			throw new SAXException( "Near " + getParser().getLocationInfo() + ": Caught and rethrew " + e.getClass().getName() + " - " + e.getMessage() );
		}
	}

	public void endElement(
		String		uri,
		String		localName,
		String		qName )
	throws SAXException
	{
	}
}
