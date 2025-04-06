
// Description: Java 11 XML SAX Element Handler for TSecGroup

/*
 *	org.msscf.msscf.CFBam
 *
 *	Copyright (c) 2020 Mark Stephen Sobkow
 *	
 *	MSS Code Factory CFSec 2.13 Security Essentials
 *	
 *	Copyright 2020-2021 Mark Stephen Sobkow mark.sobkow@gmail.com
 *	
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *	
 *	http://www.apache.org/licenses/LICENSE-2.0
 *	
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
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
 *	CFBamSaxLoaderTSecGroupParse XML SAX Element Handler implementation
 *	for TSecGroup.
 */
public class CFBamSaxLoaderTSecGroup
	extends CFLibXmlCoreElementHandler
{
	public CFBamSaxLoaderTSecGroup( CFBamSaxLoader saxLoader ) {
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
		ICFBamTSecGroupObj origBuff = null;
		ICFBamTSecGroupEditObj editBuff = null;
		// Common XML Attributes
		String attrId = null;
		// TSecGroup Attributes
		String attrName = null;
		String attrIsVisible = null;
		// TSecGroup References
		ICFBamTenantObj refTenant = null;
		// Attribute Extraction
		String attrLocalName;
		int numAttrs;
		int idxAttr;
		final String S_LocalName = "LocalName";
		try {
			assert qName.equals( "TSecGroup" );

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
			origBuff = (ICFBamTSecGroupObj)schemaObj.getTSecGroupTableObj().newInstance();
			editBuff = (ICFBamTSecGroupEditObj)origBuff.beginEdit();

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
				else if( attrLocalName.equals( "IsVisible" ) ) {
					if( attrIsVisible != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrIsVisible = attrs.getValue( idxAttr );
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
			if( ( attrIsVisible == null ) || ( attrIsVisible.length() <= 0 ) ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"IsVisible" );
			}

			// Save named attributes to context
			CFLibXmlCoreContext curContext = getParser().getCurContext();
			curContext.putNamedValue( "Id", attrId );
			curContext.putNamedValue( "Name", attrName );
			curContext.putNamedValue( "IsVisible", attrIsVisible );

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

			boolean natIsVisible;
			if( attrIsVisible.equals( "true" ) || attrIsVisible.equals( "yes" ) || attrIsVisible.equals( "1" ) ) {
				natIsVisible = true;
			}
			else if( attrIsVisible.equals( "false" ) || attrIsVisible.equals( "no" ) || attrIsVisible.equals( "0" ) ) {
				natIsVisible = false;
			}
			else {
				throw new CFLibUsageException( getClass(),
					S_ProcName,
					"Unexpected IsVisible value, must be one of true, false, yes, no, 1, or 0, not \"" + attrIsVisible + "\"" );
			}
			editBuff.setRequiredIsVisible( natIsVisible );

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
			else if( scopeObj instanceof ICFBamTenantObj ) {
				refTenant = (ICFBamTenantObj) scopeObj;
				editBuff.setRequiredContainerTenant( refTenant );
			}
			else {
				throw new CFLibUnsupportedClassException( getClass(),
					S_ProcName,
					"scopeObj",
					scopeObj,
					"ICFBamTenantObj" );
			}

			CFBamSaxLoader.LoaderBehaviourEnum loaderBehaviour = saxLoader.getTSecGroupLoaderBehaviour();
			ICFBamTSecGroupEditObj editTSecGroup = null;
			ICFBamTSecGroupObj origTSecGroup = (ICFBamTSecGroupObj)schemaObj.getTSecGroupTableObj().readTSecGroupByUNameIdx( refTenant.getRequiredId(),
			editBuff.getRequiredName() );
			if( origTSecGroup == null ) {
				editTSecGroup = editBuff;
			}
			else {
				switch( loaderBehaviour ) {
					case Insert:
						break;
					case Update:
						editTSecGroup = (ICFBamTSecGroupEditObj)origTSecGroup.beginEdit();
						editTSecGroup.setRequiredName( editBuff.getRequiredName() );
						editTSecGroup.setRequiredIsVisible( editBuff.getRequiredIsVisible() );
						break;
					case Replace:
						editTSecGroup = (ICFBamTSecGroupEditObj)origTSecGroup.beginEdit();
						editTSecGroup.deleteInstance();
						editTSecGroup = null;
						origTSecGroup = null;
						editTSecGroup = editBuff;
						break;
				}
			}

			if( editTSecGroup != null ) {
				if( origTSecGroup != null ) {
					editTSecGroup.update();
				}
				else {
					origTSecGroup = (ICFBamTSecGroupObj)editTSecGroup.create();
				}
				editTSecGroup = null;
			}

			curContext.putNamedValue( "Object", origTSecGroup );
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
