
// Description: Java 11 XML SAX Element Handler for SecForm

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
 *	CFBamSaxLoaderSecFormParse XML SAX Element Handler implementation
 *	for SecForm.
 */
public class CFBamSaxLoaderSecForm
	extends CFLibXmlCoreElementHandler
{
	public CFBamSaxLoaderSecForm( CFBamSaxLoader saxLoader ) {
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
		ICFBamSecFormObj origBuff = null;
		ICFBamSecFormEditObj editBuff = null;
		// Common XML Attributes
		String attrId = null;
		// SecForm Attributes
		String attrJEEServletMapName = null;
		// SecForm References
		ICFBamClusterObj refCluster = null;
		ICFBamSecAppObj refApplication = null;
		// Attribute Extraction
		String attrLocalName;
		int numAttrs;
		int idxAttr;
		final String S_LocalName = "LocalName";
		try {
			assert qName.equals( "SecForm" );

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
			origBuff = (ICFBamSecFormObj)schemaObj.getSecFormTableObj().newInstance();
			editBuff = (ICFBamSecFormEditObj)origBuff.beginEdit();

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
				else if( attrLocalName.equals( "JEEServletMapName" ) ) {
					if( attrJEEServletMapName != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrJEEServletMapName = attrs.getValue( idxAttr );
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
			if( attrJEEServletMapName == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"JEEServletMapName" );
			}

			// Save named attributes to context
			CFLibXmlCoreContext curContext = getParser().getCurContext();
			curContext.putNamedValue( "Id", attrId );
			curContext.putNamedValue( "JEEServletMapName", attrJEEServletMapName );

			// Convert string attributes to native Java types
			// and apply the converted attributes to the editBuff.

			Integer natId;
			if( ( attrId != null ) && ( attrId.length() > 0 ) ) {
				natId = Integer.valueOf( Integer.parseInt( attrId ) );
			}
			else {
				natId = null;
			}
			String natJEEServletMapName = attrJEEServletMapName;
			editBuff.setRequiredJEEServletMapName( natJEEServletMapName );

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
			else if( scopeObj instanceof ICFBamSecAppObj ) {
				refApplication = (ICFBamSecAppObj) scopeObj;
				editBuff.setRequiredContainerApplication( refApplication );
				refCluster = (ICFBamClusterObj)editBuff.getRequiredOwnerCluster();
			}
			else {
				throw new CFLibUnsupportedClassException( getClass(),
					S_ProcName,
					"scopeObj",
					scopeObj,
					"ICFBamSecAppObj" );
			}

			// Resolve and apply Owner reference

			if( refCluster == null ) {
				if( scopeObj instanceof ICFBamClusterObj ) {
					refCluster = (ICFBamClusterObj) scopeObj;
					editBuff.setRequiredOwnerCluster( refCluster );
				}
				else {
					throw new CFLibNullArgumentException( getClass(),
						S_ProcName,
						0,
						"Owner<Cluster>" );
				}
			}

			CFBamSaxLoader.LoaderBehaviourEnum loaderBehaviour = saxLoader.getSecFormLoaderBehaviour();
			ICFBamSecFormEditObj editSecForm = null;
			ICFBamSecFormObj origSecForm = (ICFBamSecFormObj)schemaObj.getSecFormTableObj().readSecFormByUJEEServletIdx( refCluster.getRequiredId(),
			refApplication.getRequiredSecAppId(),
			editBuff.getRequiredJEEServletMapName() );
			if( origSecForm == null ) {
				editSecForm = editBuff;
			}
			else {
				switch( loaderBehaviour ) {
					case Insert:
						break;
					case Update:
						editSecForm = (ICFBamSecFormEditObj)origSecForm.beginEdit();
						editSecForm.setRequiredJEEServletMapName( editBuff.getRequiredJEEServletMapName() );
						break;
					case Replace:
						editSecForm = (ICFBamSecFormEditObj)origSecForm.beginEdit();
						editSecForm.deleteInstance();
						editSecForm = null;
						origSecForm = null;
						editSecForm = editBuff;
						break;
				}
			}

			if( editSecForm != null ) {
				if( origSecForm != null ) {
					editSecForm.update();
				}
				else {
					origSecForm = (ICFBamSecFormObj)editSecForm.create();
				}
				editSecForm = null;
			}

			curContext.putNamedValue( "Object", origSecForm );
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
