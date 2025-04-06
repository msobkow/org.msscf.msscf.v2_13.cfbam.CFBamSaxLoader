
// Description: Java 11 XML SAX Element Handler for Index

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
 *	CFBamSaxLoaderIndexParse XML SAX Element Handler implementation
 *	for Index.
 */
public class CFBamSaxLoaderIndex
	extends CFLibXmlCoreElementHandler
{
	public CFBamSaxLoaderIndex( CFBamSaxLoader saxLoader ) {
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
		ICFBamIndexObj origBuff = null;
		ICFBamIndexEditObj editBuff = null;
		// Common XML Attributes
		String attrId = null;
		// Scope Attributes
		// Scope References
		ICFBamTenantObj refTenant = null;
		// Index Attributes
		String attrName = null;
		String attrShortName = null;
		String attrLabel = null;
		String attrShortDescription = null;
		String attrDescription = null;
		String attrDbName = null;
		String attrSuffix = null;
		String attrIsUnique = null;
		String attrIsDbMapped = null;
		String attrDefSchema = null;
		// Index References
		ICFBamTableObj refTable = null;
		ICFBamSchemaDefObj refDefSchema = null;
		ICFBamTenantObj refIdxTenant = null;
		// Attribute Extraction
		String attrLocalName;
		int numAttrs;
		int idxAttr;
		final String S_LocalName = "LocalName";
		try {
			assert qName.equals( "Index" );

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
			origBuff = (ICFBamIndexObj)schemaObj.getIndexTableObj().newInstance();
			editBuff = (ICFBamIndexEditObj)origBuff.beginEdit();

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
				else if( attrLocalName.equals( "DbName" ) ) {
					if( attrDbName != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrDbName = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "Suffix" ) ) {
					if( attrSuffix != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrSuffix = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "IsUnique" ) ) {
					if( attrIsUnique != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrIsUnique = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "IsDbMapped" ) ) {
					if( attrIsDbMapped != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrIsDbMapped = attrs.getValue( idxAttr );
				}
				else if( attrLocalName.equals( "DefSchema" ) ) {
					if( attrDefSchema != null ) {
						throw new CFLibUniqueIndexViolationException( getClass(),
							S_ProcName,
							S_LocalName,
							attrLocalName );
					}
					attrDefSchema = attrs.getValue( idxAttr );
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
			if( ( attrIsUnique == null ) || ( attrIsUnique.length() <= 0 ) ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"IsUnique" );
			}
			if( ( attrIsDbMapped == null ) || ( attrIsDbMapped.length() <= 0 ) ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"IsDbMapped" );
			}

			// Save named attributes to context
			CFLibXmlCoreContext curContext = getParser().getCurContext();
			curContext.putNamedValue( "Id", attrId );
			curContext.putNamedValue( "Name", attrName );
			curContext.putNamedValue( "ShortName", attrShortName );
			curContext.putNamedValue( "Label", attrLabel );
			curContext.putNamedValue( "ShortDescription", attrShortDescription );
			curContext.putNamedValue( "Description", attrDescription );
			curContext.putNamedValue( "DbName", attrDbName );
			curContext.putNamedValue( "Suffix", attrSuffix );
			curContext.putNamedValue( "IsUnique", attrIsUnique );
			curContext.putNamedValue( "IsDbMapped", attrIsDbMapped );
			curContext.putNamedValue( "DefSchema", attrDefSchema );

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

			String natShortName = attrShortName;
			editBuff.setOptionalShortName( natShortName );

			String natLabel = attrLabel;
			editBuff.setOptionalLabel( natLabel );

			String natShortDescription = attrShortDescription;
			editBuff.setOptionalShortDescription( natShortDescription );

			String natDescription = attrDescription;
			editBuff.setOptionalDescription( natDescription );

			String natDbName = attrDbName;
			editBuff.setOptionalDbName( natDbName );

			String natSuffix = attrSuffix;
			editBuff.setOptionalSuffix( natSuffix );

			boolean natIsUnique;
			if( attrIsUnique.equals( "true" ) || attrIsUnique.equals( "yes" ) || attrIsUnique.equals( "1" ) ) {
				natIsUnique = true;
			}
			else if( attrIsUnique.equals( "false" ) || attrIsUnique.equals( "no" ) || attrIsUnique.equals( "0" ) ) {
				natIsUnique = false;
			}
			else {
				throw new CFLibUsageException( getClass(),
					S_ProcName,
					"Unexpected IsUnique value, must be one of true, false, yes, no, 1, or 0, not \"" + attrIsUnique + "\"" );
			}
			editBuff.setRequiredIsUnique( natIsUnique );

			boolean natIsDbMapped;
			if( attrIsDbMapped.equals( "true" ) || attrIsDbMapped.equals( "yes" ) || attrIsDbMapped.equals( "1" ) ) {
				natIsDbMapped = true;
			}
			else if( attrIsDbMapped.equals( "false" ) || attrIsDbMapped.equals( "no" ) || attrIsDbMapped.equals( "0" ) ) {
				natIsDbMapped = false;
			}
			else {
				throw new CFLibUsageException( getClass(),
					S_ProcName,
					"Unexpected IsDbMapped value, must be one of true, false, yes, no, 1, or 0, not \"" + attrIsDbMapped + "\"" );
			}
			editBuff.setRequiredIsDbMapped( natIsDbMapped );

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
			else if( scopeObj instanceof ICFBamTableObj ) {
				refTable = (ICFBamTableObj) scopeObj;
				editBuff.setRequiredContainerTable( refTable );
				refIdxTenant = (ICFBamTenantObj)editBuff.getRequiredOwnerIdxTenant();
			}
			else {
				throw new CFLibUnsupportedClassException( getClass(),
					S_ProcName,
					"scopeObj",
					scopeObj,
					"ICFBamTableObj" );
			}

			// Resolve and apply Owner reference

			if( refIdxTenant == null ) {
				if( scopeObj instanceof ICFBamTenantObj ) {
					refIdxTenant = (ICFBamTenantObj) scopeObj;
					editBuff.setRequiredOwnerIdxTenant( refIdxTenant );
				}
				else {
					throw new CFLibNullArgumentException( getClass(),
						S_ProcName,
						0,
						"Owner<IdxTenant>" );
				}
			}

			refTenant = refIdxTenant;
			// Lookup refDefSchema by qualified name
			if( ( attrDefSchema != null ) && ( attrDefSchema.length() > 0 ) ) {
				refDefSchema = (ICFBamSchemaDefObj)(editBuff.getNamedObject( schemaObj.getSchemaDefTableObj().getObjQualifyingClass(),
					attrDefSchema ) );
				if( refDefSchema == null ) {
					throw new CFLibNullArgumentException( getClass(),
						S_ProcName,
						0,
						"Resolve DefSchema reference qualified name \"" + attrDefSchema + "\" to table SchemaDef" );
				}
			}
			else {
				refDefSchema = null;
			}
			editBuff.setOptionalLookupDefSchema( refDefSchema );

			CFBamSaxLoader.LoaderBehaviourEnum loaderBehaviour = saxLoader.getIndexLoaderBehaviour();
			ICFBamIndexEditObj editIndex = null;
			ICFBamIndexObj origIndex = (ICFBamIndexObj)schemaObj.getIndexTableObj().readIndexByUNameIdx( refTable.getRequiredTenantId(),
			refTable.getRequiredId(),
			editBuff.getRequiredName() );
			if( origIndex == null ) {
				editIndex = editBuff;
			}
			else {
				switch( loaderBehaviour ) {
					case Insert:
						break;
					case Update:
						editIndex = (ICFBamIndexEditObj)origIndex.beginEdit();
						editIndex.setRequiredName( editBuff.getRequiredName() );
						editIndex.setOptionalShortName( editBuff.getOptionalShortName() );
						editIndex.setOptionalLabel( editBuff.getOptionalLabel() );
						editIndex.setOptionalShortDescription( editBuff.getOptionalShortDescription() );
						editIndex.setOptionalDescription( editBuff.getOptionalDescription() );
						editIndex.setOptionalDbName( editBuff.getOptionalDbName() );
						editIndex.setOptionalSuffix( editBuff.getOptionalSuffix() );
						editIndex.setRequiredIsUnique( editBuff.getRequiredIsUnique() );
						editIndex.setRequiredIsDbMapped( editBuff.getRequiredIsDbMapped() );
						editIndex.setOptionalLookupDefSchema( editBuff.getOptionalLookupDefSchema() );
						break;
					case Replace:
						editIndex = (ICFBamIndexEditObj)origIndex.beginEdit();
						editIndex.deleteInstance();
						editIndex = null;
						origIndex = null;
						editIndex = editBuff;
						break;
				}
			}

			if( editIndex != null ) {
				if( origIndex != null ) {
					editIndex.update();
				}
				else {
					origIndex = (ICFBamIndexObj)editIndex.create();
				}
				editIndex = null;
			}

			curContext.putNamedValue( "Object", origIndex );
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
