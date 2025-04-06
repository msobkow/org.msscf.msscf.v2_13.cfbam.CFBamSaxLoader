// Description: Java 11 XML SAX Parser for CFBam.

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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.*;
import java.math.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.xerces.xni.grammars.Grammar;
import org.xml.sax.*;

import org.msscf.msscf.v2_13.cflib.CFLib.*;
import org.msscf.msscf.v2_13.cfsec.CFSec.*;
import org.msscf.msscf.v2_13.cfint.CFInt.*;
import org.msscf.msscf.v2_13.cfbam.CFBam.*;
import org.msscf.msscf.v2_13.cfsec.CFSecObj.*;
import org.msscf.msscf.v2_13.cfint.CFIntObj.*;
import org.msscf.msscf.v2_13.cfbam.CFBamObj.*;

public class CFBamSaxLoader
	extends CFLibXmlCoreSaxParser
	implements ContentHandler
{

	// The namespace URI of the supported schema
	public final static String	SCHEMA_XMLNS = "uri://org.msscf/msscf/2.0.13/cfbamloader";

	// The source for loading the supported schema
	public final static String	SCHEMA_URI = "/opt/msscf/2.0.13/xsd/cfbam-structured.xsd";
	public final static String	SCHEMA_ROOT_URI = "/xsd/cfbam-structured.xsd";
	public final static String CFSEC_XMLNS = "uri://org.msscf/msscf/2.0.13/cfsecloader";
	public final static String CFSEC_URI = "/opt/msscf/2.0.13/xsd/cfsec-structured.xsd";
	public final static String CFSEC_ROOT_URI = "/xsd/cfsec-structured.xsd";

	public final static String CFINT_XMLNS = "uri://org.msscf/msscf/2.0.13/cfintloader";
	public final static String CFINT_URI = "/opt/msscf/2.0.13/xsd/cfint-structured.xsd";
	public final static String CFINT_ROOT_URI = "/xsd/cfint-structured.xsd";

	protected Grammar myGrammar = null;

	// The schema instance to load in to

	private ICFBamSchemaObj schemaObj = null;

	// The cluster to use for loading

	private ICFSecClusterObj useCluster = null;

	public ICFSecClusterObj getUseCluster() {
		return( useCluster );
	}

	public void setUseCluster( ICFSecClusterObj value ) {
		useCluster = value;
	}

	// The tenant to use for loading

	private ICFSecTenantObj useTenant = null;

	public ICFSecTenantObj getUseTenant() {
		return( useTenant );
	}

	public void setUseTenant( ICFSecTenantObj value ) {
		useTenant = value;
	}

	// Loader behaviour configuration attributes

	public enum LoaderBehaviourEnum {
		Insert,
		Update,
		Replace
	};
	private LoaderBehaviourEnum atomLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum blobColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum blobDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum blobTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum boolColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum boolDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum boolTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum chainLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum clearDepLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum clearSubDep1LoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum clearSubDep2LoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum clearSubDep3LoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum clearTopDepLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum clusterLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum dateColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum dateDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum dateTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum delDepLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum delSubDep1LoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum delSubDep2LoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum delSubDep3LoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum delTopDepLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum doubleColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum doubleDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum doubleTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum enumDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum enumTagLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum enumTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum floatColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum floatDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum floatTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum hostNodeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum iSOCcyLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum iSOCtryLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum iSOCtryCcyLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum iSOCtryLangLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum iSOLangLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum iSOTZoneLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum id16GenLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum id32GenLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum id64GenLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum indexLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum indexColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum int16ColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum int16DefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum int16TypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum int32ColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum int32DefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum int32TypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum int64ColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum int64DefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum int64TypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum licenseLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum majorVersionLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum mimeTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum minorVersionLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum nmTokenColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum nmTokenDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum nmTokenTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum nmTokensColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum nmTokensDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum nmTokensTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum numberColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum numberDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum numberTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum paramLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum popDepLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum popSubDep1LoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum popSubDep2LoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum popSubDep3LoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum popTopDepLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum relationLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum relationColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum schemaDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum schemaRefLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum scopeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum secAppLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum secDeviceLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum secFormLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum secGroupLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum secGroupFormLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum secGrpIncLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum secGrpMembLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum secSessionLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum secUserLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum serverListFuncLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum serverMethodLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum serverObjFuncLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum serverProcLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum serviceLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum serviceTypeLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum stringColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum stringDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum stringTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum subProjectLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum sysClusterLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum tSecGroupLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum tSecGrpIncLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum tSecGrpMembLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum tZDateColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tZDateDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tZDateTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tZTimeColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tZTimeDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tZTimeTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tZTimestampColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tZTimestampDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tZTimestampTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tableLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tableColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tenantLoaderBehaviour = LoaderBehaviourEnum.Insert;
	private LoaderBehaviourEnum textColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum textDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum textTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum timeColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum timeDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum timeTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum timestampColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum timestampDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum timestampTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tldLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tokenColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tokenDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum tokenTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum topDomainLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum topProjectLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uInt16ColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uInt16DefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uInt16TypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uInt32ColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uInt32DefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uInt32TypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uInt64ColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uInt64DefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uInt64TypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uRLProtocolLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uuidColLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uuidDefLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uuidGenLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum uuidTypeLoaderBehaviour = LoaderBehaviourEnum.Update;
	private LoaderBehaviourEnum valueLoaderBehaviour = LoaderBehaviourEnum.Update;


	// Constructors

	public CFBamSaxLoader() {
		super();
		setRootElementHandler( getSaxRootHandler() );
		if( myGrammar == null ) {
			InputStream input;
			File file = new File( SCHEMA_URI );
			if( file.exists() ) {
				try {
					input = new FileInputStream( file );
				}
				catch( Exception e ) {
					input = null;
				}
				if( input != null ) {
					myGrammar = addToGrammarPool( SCHEMA_URI, input );
				}
			}
			else {
				input = getClass().getResourceAsStream( SCHEMA_ROOT_URI );
				if( input != null ) {
					myGrammar = addToGrammarPool( SCHEMA_ROOT_URI, input );
				}
			}
			file = new File( CFSEC_URI );
			if( file.exists() ) {
				try {
					input = new FileInputStream( file );
				}
				catch( Exception e ) {
					input = null;
				}
				if( input != null ) {
					addToGrammarPool( CFSEC_URI, input );
				}
			}
			else {
				input = getClass().getResourceAsStream( CFSEC_ROOT_URI );
				if( input != null ) {
					addToGrammarPool( CFSEC_ROOT_URI, input );
				}
			}
			file = new File( CFINT_URI );
			if( file.exists() ) {
				try {
					input = new FileInputStream( file );
				}
				catch( Exception e ) {
					input = null;
				}
				if( input != null ) {
					addToGrammarPool( CFINT_URI, input );
				}
			}
			else {
				input = getClass().getResourceAsStream( CFINT_ROOT_URI );
				if( input != null ) {
					addToGrammarPool( CFINT_ROOT_URI, input );
				}
			}
		}
		initParser();
	}

	public CFBamSaxLoader( ICFLibMessageLog logger ) {
		super( logger );
		setRootElementHandler( getSaxRootHandler() );
		if( myGrammar == null ) {
			InputStream input;
			File file = new File( SCHEMA_URI );
			if( file.exists() ) {
				try {
					input = new FileInputStream( file );
				}
				catch( Exception e ) {
					input = null;
				}
				if( input != null ) {
					myGrammar = addToGrammarPool( SCHEMA_URI, input );
				}
			}
			else {
				input = getClass().getResourceAsStream( SCHEMA_ROOT_URI );
				if( input != null ) {
					myGrammar = addToGrammarPool( SCHEMA_ROOT_URI, input );
				}
			}
			file = new File( CFSEC_URI );
			if( file.exists() ) {
				try {
					input = new FileInputStream( file );
				}
				catch( Exception e ) {
					input = null;
				}
				if( input != null ) {
					addToGrammarPool( CFSEC_URI, input );
				}
			}
			else {
				input = getClass().getResourceAsStream( CFSEC_ROOT_URI );
				if( input != null ) {
					addToGrammarPool( CFSEC_ROOT_URI, input );
				}
			}
			file = new File( CFINT_URI );
			if( file.exists() ) {
				try {
					input = new FileInputStream( file );
				}
				catch( Exception e ) {
					input = null;
				}
				if( input != null ) {
					addToGrammarPool( CFINT_URI, input );
				}
			}
			else {
				input = getClass().getResourceAsStream( CFINT_ROOT_URI );
				if( input != null ) {
					addToGrammarPool( CFINT_ROOT_URI, input );
				}
			}
		}
		initParser();
	}

	// Element Handler instances

	private CFBamSaxLoaderAtom atomHandler = null;
	private CFBamSaxLoaderBlobCol blobColHandler = null;
	private CFBamSaxLoaderBlobDef blobDefHandler = null;
	private CFBamSaxLoaderBlobType blobTypeHandler = null;
	private CFBamSaxLoaderBoolCol boolColHandler = null;
	private CFBamSaxLoaderBoolDef boolDefHandler = null;
	private CFBamSaxLoaderBoolType boolTypeHandler = null;
	private CFBamSaxLoaderChain chainHandler = null;
	private CFBamSaxLoaderClearDep clearDepHandler = null;
	private CFBamSaxLoaderClearSubDep1 clearSubDep1Handler = null;
	private CFBamSaxLoaderClearSubDep2 clearSubDep2Handler = null;
	private CFBamSaxLoaderClearSubDep3 clearSubDep3Handler = null;
	private CFBamSaxLoaderClearTopDep clearTopDepHandler = null;
	private CFBamSaxLoaderCluster clusterHandler = null;
	private CFBamSaxLoaderDateCol dateColHandler = null;
	private CFBamSaxLoaderDateDef dateDefHandler = null;
	private CFBamSaxLoaderDateType dateTypeHandler = null;
	private CFBamSaxLoaderDelDep delDepHandler = null;
	private CFBamSaxLoaderDelSubDep1 delSubDep1Handler = null;
	private CFBamSaxLoaderDelSubDep2 delSubDep2Handler = null;
	private CFBamSaxLoaderDelSubDep3 delSubDep3Handler = null;
	private CFBamSaxLoaderDelTopDep delTopDepHandler = null;
	private CFBamSaxLoaderDoubleCol doubleColHandler = null;
	private CFBamSaxLoaderDoubleDef doubleDefHandler = null;
	private CFBamSaxLoaderDoubleType doubleTypeHandler = null;
	private CFBamSaxLoaderEnumDef enumDefHandler = null;
	private CFBamSaxLoaderEnumTag enumTagHandler = null;
	private CFBamSaxLoaderEnumType enumTypeHandler = null;
	private CFBamSaxLoaderFloatCol floatColHandler = null;
	private CFBamSaxLoaderFloatDef floatDefHandler = null;
	private CFBamSaxLoaderFloatType floatTypeHandler = null;
	private CFBamSaxLoaderHostNode hostNodeHandler = null;
	private CFBamSaxLoaderISOCcy iSOCcyHandler = null;
	private CFBamSaxLoaderISOCtry iSOCtryHandler = null;
	private CFBamSaxLoaderISOCtryCcy iSOCtryCcyHandler = null;
	private CFBamSaxLoaderISOCtryLang iSOCtryLangHandler = null;
	private CFBamSaxLoaderISOLang iSOLangHandler = null;
	private CFBamSaxLoaderISOTZone iSOTZoneHandler = null;
	private CFBamSaxLoaderId16Gen id16GenHandler = null;
	private CFBamSaxLoaderId32Gen id32GenHandler = null;
	private CFBamSaxLoaderId64Gen id64GenHandler = null;
	private CFBamSaxLoaderIndex indexHandler = null;
	private CFBamSaxLoaderIndexCol indexColHandler = null;
	private CFBamSaxLoaderInt16Col int16ColHandler = null;
	private CFBamSaxLoaderInt16Def int16DefHandler = null;
	private CFBamSaxLoaderInt16Type int16TypeHandler = null;
	private CFBamSaxLoaderInt32Col int32ColHandler = null;
	private CFBamSaxLoaderInt32Def int32DefHandler = null;
	private CFBamSaxLoaderInt32Type int32TypeHandler = null;
	private CFBamSaxLoaderInt64Col int64ColHandler = null;
	private CFBamSaxLoaderInt64Def int64DefHandler = null;
	private CFBamSaxLoaderInt64Type int64TypeHandler = null;
	private CFBamSaxLoaderLicense licenseHandler = null;
	private CFBamSaxLoaderMajorVersion majorVersionHandler = null;
	private CFBamSaxLoaderMimeType mimeTypeHandler = null;
	private CFBamSaxLoaderMinorVersion minorVersionHandler = null;
	private CFBamSaxLoaderNmTokenCol nmTokenColHandler = null;
	private CFBamSaxLoaderNmTokenDef nmTokenDefHandler = null;
	private CFBamSaxLoaderNmTokenType nmTokenTypeHandler = null;
	private CFBamSaxLoaderNmTokensCol nmTokensColHandler = null;
	private CFBamSaxLoaderNmTokensDef nmTokensDefHandler = null;
	private CFBamSaxLoaderNmTokensType nmTokensTypeHandler = null;
	private CFBamSaxLoaderNumberCol numberColHandler = null;
	private CFBamSaxLoaderNumberDef numberDefHandler = null;
	private CFBamSaxLoaderNumberType numberTypeHandler = null;
	private CFBamSaxLoaderParam paramHandler = null;
	private CFBamSaxLoaderPopDep popDepHandler = null;
	private CFBamSaxLoaderPopSubDep1 popSubDep1Handler = null;
	private CFBamSaxLoaderPopSubDep2 popSubDep2Handler = null;
	private CFBamSaxLoaderPopSubDep3 popSubDep3Handler = null;
	private CFBamSaxLoaderPopTopDep popTopDepHandler = null;
	private CFBamSaxLoaderRelation relationHandler = null;
	private CFBamSaxLoaderRelationCol relationColHandler = null;
	private CFBamSaxLoaderSchemaDef schemaDefHandler = null;
	private CFBamSaxLoaderSchemaDefCafeSchemaObjImport schemaDefCafeSchemaObjImportHandler = null;
	private CFBamSaxLoaderSchemaDefCafeSchemaObjInterface schemaDefCafeSchemaObjInterfaceHandler = null;
	private CFBamSaxLoaderSchemaDefCafeSchemaObjMembers schemaDefCafeSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCafeSchemaObjImplementation schemaDefCafeSchemaObjImplementationHandler = null;
	private CFBamSaxLoaderSchemaDefCafeDb2LUWSchemaObjMembers schemaDefCafeDb2LUWSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCafeDb2LUWSchemaObjImpl schemaDefCafeDb2LUWSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCafeDb2LUWSchemaObjImport schemaDefCafeDb2LUWSchemaObjImportHandler = null;
	private CFBamSaxLoaderSchemaDefCafeMSSqlSchemaObjMembers schemaDefCafeMSSqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCafeMSSqlSchemaObjImpl schemaDefCafeMSSqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCafeMSSqlSchemaObjImport schemaDefCafeMSSqlSchemaObjImportHandler = null;
	private CFBamSaxLoaderSchemaDefCafeMySqlSchemaObjMembers schemaDefCafeMySqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCafeMySqlSchemaObjImpl schemaDefCafeMySqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCafeMySqlSchemaObjImport schemaDefCafeMySqlSchemaObjImportHandler = null;
	private CFBamSaxLoaderSchemaDefCafeOracleSchemaObjMembers schemaDefCafeOracleSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCafeOracleSchemaObjImpl schemaDefCafeOracleSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCafeOracleSchemaObjImport schemaDefCafeOracleSchemaObjImportHandler = null;
	private CFBamSaxLoaderSchemaDefCafePgSqlSchemaObjMembers schemaDefCafePgSqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCafePgSqlSchemaObjImpl schemaDefCafePgSqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCafePgSqlSchemaObjImport schemaDefCafePgSqlSchemaObjImportHandler = null;
	private CFBamSaxLoaderSchemaDefCafeRamSchemaObjMembers schemaDefCafeRamSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCafeRamSchemaObjImpl schemaDefCafeRamSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCafeRamSchemaObjImport schemaDefCafeRamSchemaObjImportHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgSchemaImport schemaDefCafeXMsgSchemaImportHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgSchemaFormatters schemaDefCafeXMsgSchemaFormattersHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgClientSchemaImport schemaDefCafeXMsgClientSchemaImportHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgClientSchemaBody schemaDefCafeXMsgClientSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaBody schemaDefCafeXMsgRqstSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaImport schemaDefCafeXMsgRqstSchemaImportHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaWireParsers schemaDefCafeXMsgRqstSchemaWireParsersHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaXsdSpec schemaDefCafeXMsgRqstSchemaXsdSpecHandler = null;
	private CFBamSaxLoaderSchemaDefCafeSchemaXsdElementList schemaDefCafeSchemaXsdElementListHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaBody schemaDefCafeXMsgRspnSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaImport schemaDefCafeXMsgRspnSchemaImportHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaWireParsers schemaDefCafeXMsgRspnSchemaWireParsersHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaXsdElementList schemaDefCafeXMsgRspnSchemaXsdElementListHandler = null;
	private CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaXsdSpec schemaDefCafeXMsgRspnSchemaXsdSpecHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusSchemaObjInclude schemaDefCPlusSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusSchemaObjInterface schemaDefCPlusSchemaObjInterfaceHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusSchemaObjMembers schemaDefCPlusSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusSchemaObjImplementation schemaDefCPlusSchemaObjImplementationHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusDb2LUWSchemaObjMembers schemaDefCPlusDb2LUWSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusDb2LUWSchemaObjImpl schemaDefCPlusDb2LUWSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusDb2LUWSchemaObjInclude schemaDefCPlusDb2LUWSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusMSSqlSchemaObjMembers schemaDefCPlusMSSqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusMSSqlSchemaObjImpl schemaDefCPlusMSSqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusMSSqlSchemaObjInclude schemaDefCPlusMSSqlSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusMySqlSchemaObjMembers schemaDefCPlusMySqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusMySqlSchemaObjImpl schemaDefCPlusMySqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusMySqlSchemaObjInclude schemaDefCPlusMySqlSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusOracleSchemaObjMembers schemaDefCPlusOracleSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusOracleSchemaObjImpl schemaDefCPlusOracleSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusOracleSchemaObjInclude schemaDefCPlusOracleSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusPgSqlSchemaObjMembers schemaDefCPlusPgSqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusPgSqlSchemaObjImpl schemaDefCPlusPgSqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusPgSqlSchemaObjInclude schemaDefCPlusPgSqlSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusRamSchemaObjMembers schemaDefCPlusRamSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusRamSchemaObjImpl schemaDefCPlusRamSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusRamSchemaObjInclude schemaDefCPlusRamSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgSchemaInclude schemaDefCPlusXMsgSchemaIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgSchemaFormatters schemaDefCPlusXMsgSchemaFormattersHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgClientSchemaInclude schemaDefCPlusXMsgClientSchemaIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgClientSchemaBody schemaDefCPlusXMsgClientSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaBody schemaDefCPlusXMsgRqstSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaInclude schemaDefCPlusXMsgRqstSchemaIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaWireParsers schemaDefCPlusXMsgRqstSchemaWireParsersHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaXsdSpec schemaDefCPlusXMsgRqstSchemaXsdSpecHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaXsdElementList schemaDefCPlusXMsgRqstSchemaXsdElementListHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXmsgRspnSchemaBody schemaDefCPlusXmsgRspnSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaInclude schemaDefCPlusXMsgRspnSchemaIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaWireParsers schemaDefCPlusXMsgRspnSchemaWireParsersHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaXsdElementList schemaDefCPlusXMsgRspnSchemaXsdElementListHandler = null;
	private CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaXsdSpec schemaDefCPlusXMsgRspnSchemaXsdSpecHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusSchemaObjInclude schemaDefHPlusSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusSchemaObjInterface schemaDefHPlusSchemaObjInterfaceHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusSchemaObjMembers schemaDefHPlusSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusSchemaObjImplementation schemaDefHPlusSchemaObjImplementationHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusDb2LUWSchemaObjMembers schemaDefHPlusDb2LUWSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusDb2LUWSchemaObjImpl schemaDefHPlusDb2LUWSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusDb2LUWSchemaObjInclude schemaDefHPlusDb2LUWSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusMSSqlSchemaObjMembers schemaDefHPlusMSSqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusMSSqlSchemaObjImpl schemaDefHPlusMSSqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusMSSqlSchemaObjInclude schemaDefHPlusMSSqlSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusMySqlSchemaObjMembers schemaDefHPlusMySqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusMySqlSchemaObjImpl schemaDefHPlusMySqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusMySqlSchemaObjInclude schemaDefHPlusMySqlSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusOracleSchemaObjMembers schemaDefHPlusOracleSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusOracleSchemaObjImpl schemaDefHPlusOracleSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusOracleSchemaObjInclude schemaDefHPlusOracleSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusPgSqlSchemaObjMembers schemaDefHPlusPgSqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusPgSqlSchemaObjImpl schemaDefHPlusPgSqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusPgSqlSchemaObjInclude schemaDefHPlusPgSqlSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusRamSchemaObjMembers schemaDefHPlusRamSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusRamSchemaObjImpl schemaDefHPlusRamSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusRamSchemaObjInclude schemaDefHPlusRamSchemaObjIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgSchemaInclude schemaDefHPlusXMsgSchemaIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgSchemaFormatters schemaDefHPlusXMsgSchemaFormattersHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgClientSchemaInclude schemaDefHPlusXMsgClientSchemaIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgClientSchemaBody schemaDefHPlusXMsgClientSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaBody schemaDefHPlusXMsgRqstSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaInclude schemaDefHPlusXMsgRqstSchemaIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaWireParsers schemaDefHPlusXMsgRqstSchemaWireParsersHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaXsdSpec schemaDefHPlusXMsgRqstSchemaXsdSpecHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaXsdElementList schemaDefHPlusXMsgRqstSchemaXsdElementListHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaBody schemaDefHPlusXMsgRspnSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaInclude schemaDefHPlusXMsgRspnSchemaIncludeHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaWireParsers schemaDefHPlusXMsgRspnSchemaWireParsersHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaXsdElementList schemaDefHPlusXMsgRspnSchemaXsdElementListHandler = null;
	private CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaXsdSpec schemaDefHPlusXMsgRspnSchemaXsdSpecHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpSchemaObjUsing schemaDefCSharpSchemaObjUsingHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpSchemaObjInterface schemaDefCSharpSchemaObjInterfaceHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpSchemaObjMembers schemaDefCSharpSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpSchemaObjImplementation schemaDefCSharpSchemaObjImplementationHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpDb2LUWSchemaObjMembers schemaDefCSharpDb2LUWSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpDb2LUWSchemaObjImpl schemaDefCSharpDb2LUWSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpDb2LUWSchemaObjUsing schemaDefCSharpDb2LUWSchemaObjUsingHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpMSSqlSchemaObjMembers schemaDefCSharpMSSqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpMSSqlSchemaObjImpl schemaDefCSharpMSSqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpMSSqlSchemaObjUsing schemaDefCSharpMSSqlSchemaObjUsingHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpMySqlSchemaObjMembers schemaDefCSharpMySqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpMySqlSchemaObjImpl schemaDefCSharpMySqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpMySqlSchemaObjUsing schemaDefCSharpMySqlSchemaObjUsingHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpOracleSchemaObjMembers schemaDefCSharpOracleSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpOracleSchemaObjImpl schemaDefCSharpOracleSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpOracleSchemaObjUsing schemaDefCSharpOracleSchemaObjUsingHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpPgSqlSchemaObjMembers schemaDefCSharpPgSqlSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpPgSqlSchemaObjImpl schemaDefCSharpPgSqlSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpPgSqlSchemaObjUsing schemaDefCSharpPgSqlSchemaObjUsingHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpRamSchemaObjMembers schemaDefCSharpRamSchemaObjMembersHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpRamSchemaObjImpl schemaDefCSharpRamSchemaObjImplHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpRamSchemaObjUsing schemaDefCSharpRamSchemaObjUsingHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgSchemaUsing schemaDefCSharpXMsgSchemaUsingHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgSchemaFormatters schemaDefCSharpXMsgSchemaFormattersHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgClientSchemaUsing schemaDefCSharpXMsgClientSchemaUsingHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgClientSchemaBody schemaDefCSharpXMsgClientSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaBody schemaDefCSharpXMsgRqstSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaUsing schemaDefCSharpXMsgRqstSchemaUsingHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaWireParsers schemaDefCSharpXMsgRqstSchemaWireParsersHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaXsdSpec schemaDefCSharpXMsgRqstSchemaXsdSpecHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaXsdElementList schemaDefCSharpXMsgRqstSchemaXsdElementListHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaBody schemaDefCSharpXMsgRspnSchemaBodyHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaUsing schemaDefCSharpXMsgRspnSchemaUsingHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaWireParsers schemaDefCSharpXMsgRspnSchemaWireParsersHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaXsdElementList schemaDefCSharpXMsgRspnSchemaXsdElementListHandler = null;
	private CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaXsdSpec schemaDefCSharpXMsgRspnSchemaXsdSpecHandler = null;
	private CFBamSaxLoaderSchemaRef schemaRefHandler = null;
	private CFBamSaxLoaderScope scopeHandler = null;
	private CFBamSaxLoaderSecApp secAppHandler = null;
	private CFBamSaxLoaderSecDevice secDeviceHandler = null;
	private CFBamSaxLoaderSecForm secFormHandler = null;
	private CFBamSaxLoaderSecGroup secGroupHandler = null;
	private CFBamSaxLoaderSecGroupForm secGroupFormHandler = null;
	private CFBamSaxLoaderSecGrpInc secGrpIncHandler = null;
	private CFBamSaxLoaderSecGrpMemb secGrpMembHandler = null;
	private CFBamSaxLoaderSecSession secSessionHandler = null;
	private CFBamSaxLoaderSecUser secUserHandler = null;
	private CFBamSaxLoaderServerListFunc serverListFuncHandler = null;
	private CFBamSaxLoaderServerMethod serverMethodHandler = null;
	private CFBamSaxLoaderServerObjFunc serverObjFuncHandler = null;
	private CFBamSaxLoaderServerProc serverProcHandler = null;
	private CFBamSaxLoaderService serviceHandler = null;
	private CFBamSaxLoaderServiceType serviceTypeHandler = null;
	private CFBamSaxLoaderStringCol stringColHandler = null;
	private CFBamSaxLoaderStringDef stringDefHandler = null;
	private CFBamSaxLoaderStringType stringTypeHandler = null;
	private CFBamSaxLoaderSubProject subProjectHandler = null;
	private CFBamSaxLoaderSysCluster sysClusterHandler = null;
	private CFBamSaxLoaderTSecGroup tSecGroupHandler = null;
	private CFBamSaxLoaderTSecGrpInc tSecGrpIncHandler = null;
	private CFBamSaxLoaderTSecGrpMemb tSecGrpMembHandler = null;
	private CFBamSaxLoaderTZDateCol tZDateColHandler = null;
	private CFBamSaxLoaderTZDateDef tZDateDefHandler = null;
	private CFBamSaxLoaderTZDateType tZDateTypeHandler = null;
	private CFBamSaxLoaderTZTimeCol tZTimeColHandler = null;
	private CFBamSaxLoaderTZTimeDef tZTimeDefHandler = null;
	private CFBamSaxLoaderTZTimeType tZTimeTypeHandler = null;
	private CFBamSaxLoaderTZTimestampCol tZTimestampColHandler = null;
	private CFBamSaxLoaderTZTimestampDef tZTimestampDefHandler = null;
	private CFBamSaxLoaderTZTimestampType tZTimestampTypeHandler = null;
	private CFBamSaxLoaderTable tableHandler = null;
	private CFBamSaxLoaderTableCafeObjMembers tableCafeObjMembersHandler = null;
	private CFBamSaxLoaderTableCafeObjInterface tableCafeObjInterfaceHandler = null;
	private CFBamSaxLoaderTableCafeObjImport tableCafeObjImportHandler = null;
	private CFBamSaxLoaderTableCafeObjImplementation tableCafeObjImplementationHandler = null;
	private CFBamSaxLoaderTableCafeEditObjMembers tableCafeEditObjMembersHandler = null;
	private CFBamSaxLoaderTableCafeEditObjInterface tableCafeEditObjInterfaceHandler = null;
	private CFBamSaxLoaderTableCafeEditObjImport tableCafeEditObjImportHandler = null;
	private CFBamSaxLoaderTableCafeEditObjImplementation tableCafeEditObjImplementationHandler = null;
	private CFBamSaxLoaderTableCafeTableImport tableCafeTableImportHandler = null;
	private CFBamSaxLoaderTableCafeTableMembers tableCafeTableMembersHandler = null;
	private CFBamSaxLoaderTableCafeTableInterface tableCafeTableInterfaceHandler = null;
	private CFBamSaxLoaderTableCafeTableImplementation tableCafeTableImplementationHandler = null;
	private CFBamSaxLoaderTableCafeTableObjImport tableCafeTableObjImportHandler = null;
	private CFBamSaxLoaderTableCafeTableObjMembers tableCafeTableObjMembersHandler = null;
	private CFBamSaxLoaderTableCafeTableObjInterface tableCafeTableObjInterfaceHandler = null;
	private CFBamSaxLoaderTableCafeTableObjImplementation tableCafeTableObjImplementationHandler = null;
	private CFBamSaxLoaderTableCafeDb2LUWTableImport tableCafeDb2LUWTableImportHandler = null;
	private CFBamSaxLoaderTableCafeDb2LUWTableMembers tableCafeDb2LUWTableMembersHandler = null;
	private CFBamSaxLoaderTableCafeDb2LUWTableImplementation tableCafeDb2LUWTableImplementationHandler = null;
	private CFBamSaxLoaderTableCafeMSSqlTableImport tableCafeMSSqlTableImportHandler = null;
	private CFBamSaxLoaderTableCafeMSSqlTableMembers tableCafeMSSqlTableMembersHandler = null;
	private CFBamSaxLoaderTableCafeMSSqlTableImplementation tableCafeMSSqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableCafeMySqlTableImport tableCafeMySqlTableImportHandler = null;
	private CFBamSaxLoaderTableCafeMySqlTableMembers tableCafeMySqlTableMembersHandler = null;
	private CFBamSaxLoaderTableCafeMySqlTableImplementation tableCafeMySqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableCafeOracleTableImport tableCafeOracleTableImportHandler = null;
	private CFBamSaxLoaderTableCafeOracleTableMembers tableCafeOracleTableMembersHandler = null;
	private CFBamSaxLoaderTableCafeOracleTableImplementation tableCafeOracleTableImplementationHandler = null;
	private CFBamSaxLoaderTableCafePgSqlTableImport tableCafePgSqlTableImportHandler = null;
	private CFBamSaxLoaderTableCafePgSqlTableMembers tableCafePgSqlTableMembersHandler = null;
	private CFBamSaxLoaderTableCafePgSqlTableImplementation tableCafePgSqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableCafeRamTableImport tableCafeRamTableImportHandler = null;
	private CFBamSaxLoaderTableCafeRamTableMembers tableCafeRamTableMembersHandler = null;
	private CFBamSaxLoaderTableCafeRamTableImplementation tableCafeRamTableImplementationHandler = null;
	private CFBamSaxLoaderTableCafeSaxLoaderImport tableCafeSaxLoaderImportHandler = null;
	private CFBamSaxLoaderTableCafeSaxLoaderStartElement tableCafeSaxLoaderStartElementHandler = null;
	private CFBamSaxLoaderTableCafeSaxLoaderEndElement tableCafeSaxLoaderEndElementHandler = null;
	private CFBamSaxLoaderTableCafeXMsgTableImport tableCafeXMsgTableImportHandler = null;
	private CFBamSaxLoaderTableCafeXMsgTableformatters tableCafeXMsgTableformattersHandler = null;
	private CFBamSaxLoaderTableCafeXMsgRqstTableImport tableCafeXMsgRqstTableImportHandler = null;
	private CFBamSaxLoaderTableCafeXMsgRspnTableImport tableCafeXMsgRspnTableImportHandler = null;
	private CFBamSaxLoaderTableCafeXMsgClientTableImport tableCafeXMsgClientTableImportHandler = null;
	private CFBamSaxLoaderTableCafeXMsgRqstTableBody tableCafeXMsgRqstTableBodyHandler = null;
	private CFBamSaxLoaderTableCafeXMsgRspnTableBody tableCafeXMsgRspnTableBodyHandler = null;
	private CFBamSaxLoaderTableCafeXMsgClientTableBody tableCafeXMsgClientTableBodyHandler = null;
	private CFBamSaxLoaderTableCPlusObjMembers tableCPlusObjMembersHandler = null;
	private CFBamSaxLoaderTableCPlusObjInterface tableCPlusObjInterfaceHandler = null;
	private CFBamSaxLoaderTableCPlusObjInclude tableCPlusObjIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusObjImplementation tableCPlusObjImplementationHandler = null;
	private CFBamSaxLoaderTableCPlusEditObjMembers tableCPlusEditObjMembersHandler = null;
	private CFBamSaxLoaderTableCPlusEditObjInterace tableCPlusEditObjInteraceHandler = null;
	private CFBamSaxLoaderTableCPlusEditObjInclude tableCPlusEditObjIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusEditObjImplementation tableCPlusEditObjImplementationHandler = null;
	private CFBamSaxLoaderTableCPlusTableInclude tableCPlusTableIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusTableMembers tableCPlusTableMembersHandler = null;
	private CFBamSaxLoaderTableCPlusTableInterface tableCPlusTableInterfaceHandler = null;
	private CFBamSaxLoaderTableCPlusTableImplementation tableCPlusTableImplementationHandler = null;
	private CFBamSaxLoaderTableCPlusTableObjInclude tableCPlusTableObjIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusTableObjMembers tableCPlusTableObjMembersHandler = null;
	private CFBamSaxLoaderTableCPlusTableObjInterface tableCPlusTableObjInterfaceHandler = null;
	private CFBamSaxLoaderTableCPlusTableObjImplementation tableCPlusTableObjImplementationHandler = null;
	private CFBamSaxLoaderTableCPlusDb2LUWTableInclude tableCPlusDb2LUWTableIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusDb2LUWTableMembers tableCPlusDb2LUWTableMembersHandler = null;
	private CFBamSaxLoaderTableCPlusDb2LUWTableImplementation tableCPlusDb2LUWTableImplementationHandler = null;
	private CFBamSaxLoaderTableCPlusMSSqlTableInclude tableCPlusMSSqlTableIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusMSSqlTableMembers tableCPlusMSSqlTableMembersHandler = null;
	private CFBamSaxLoaderTableCPlusMSSqlTableImplementation tableCPlusMSSqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableCPlusMySqlTableInclude tableCPlusMySqlTableIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusMySqlTableMembers tableCPlusMySqlTableMembersHandler = null;
	private CFBamSaxLoaderTableCPlusMySqlTableImplementation tableCPlusMySqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableCPlusOracleTableInclude tableCPlusOracleTableIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusOracleTableMembers tableCPlusOracleTableMembersHandler = null;
	private CFBamSaxLoaderTableCPlusOracleTableImplementation tableCPlusOracleTableImplementationHandler = null;
	private CFBamSaxLoaderTableCPlusPgSqlTableMembers tableCPlusPgSqlTableMembersHandler = null;
	private CFBamSaxLoaderTableCPlusPgSqlTableImplementation tableCPlusPgSqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableCPlusRamTableInclude tableCPlusRamTableIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusRamTableMembers tableCPlusRamTableMembersHandler = null;
	private CFBamSaxLoaderTableCPlusRamTableImplementation tableCPlusRamTableImplementationHandler = null;
	private CFBamSaxLoaderTableCPlusSaxLoaderInclude tableCPlusSaxLoaderIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusSaxLoaderStartElement tableCPlusSaxLoaderStartElementHandler = null;
	private CFBamSaxLoaderTableCPlusSaxLoaderEndElement tableCPlusSaxLoaderEndElementHandler = null;
	private CFBamSaxLoaderTableCPlusXMsgTableInclude tableCPlusXMsgTableIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusXMsgTableFormatters tableCPlusXMsgTableFormattersHandler = null;
	private CFBamSaxLoaderTableCPlusXMsgRqstTableInclude tableCPlusXMsgRqstTableIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusXMsgRspnTableInclude tableCPlusXMsgRspnTableIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusXMsgClientTableInclude tableCPlusXMsgClientTableIncludeHandler = null;
	private CFBamSaxLoaderTableCPlusXMsgRqstTableBody tableCPlusXMsgRqstTableBodyHandler = null;
	private CFBamSaxLoaderTableCPlusXMsgRspnTableBody tableCPlusXMsgRspnTableBodyHandler = null;
	private CFBamSaxLoaderTableCPlusXMsgClientTableBody tableCPlusXMsgClientTableBodyHandler = null;
	private CFBamSaxLoaderTableHPlusObjMembers tableHPlusObjMembersHandler = null;
	private CFBamSaxLoaderTableHPlusObjInterface tableHPlusObjInterfaceHandler = null;
	private CFBamSaxLoaderTableHPlusObjInclude tableHPlusObjIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusObjImplemntation tableHPlusObjImplemntationHandler = null;
	private CFBamSaxLoaderTableHPlusEditObjMembers tableHPlusEditObjMembersHandler = null;
	private CFBamSaxLoaderTableHPlusEditObjInterface tableHPlusEditObjInterfaceHandler = null;
	private CFBamSaxLoaderTableHPlusEditObjInclude tableHPlusEditObjIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusEditObjImplementation tableHPlusEditObjImplementationHandler = null;
	private CFBamSaxLoaderTableHPlusTableInclude tableHPlusTableIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusTableMembers tableHPlusTableMembersHandler = null;
	private CFBamSaxLoaderTableHPlusTableInterface tableHPlusTableInterfaceHandler = null;
	private CFBamSaxLoaderTableHPlusTableImplementation tableHPlusTableImplementationHandler = null;
	private CFBamSaxLoaderTableHPlusTableObjInclude tableHPlusTableObjIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusTableObjMembers tableHPlusTableObjMembersHandler = null;
	private CFBamSaxLoaderTableHPlusTableObjInterface tableHPlusTableObjInterfaceHandler = null;
	private CFBamSaxLoaderTableHPlusTableObjImplementation tableHPlusTableObjImplementationHandler = null;
	private CFBamSaxLoaderTableHPlusDb2LUWTableInclude tableHPlusDb2LUWTableIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusDb2LUWTableMembers tableHPlusDb2LUWTableMembersHandler = null;
	private CFBamSaxLoaderTableHPlusDb2LUWTableImplementation tableHPlusDb2LUWTableImplementationHandler = null;
	private CFBamSaxLoaderTableHPlusMSSqlTableInclude tableHPlusMSSqlTableIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusMSSqlTableMembers tableHPlusMSSqlTableMembersHandler = null;
	private CFBamSaxLoaderTableHPlusMSSqlTableImplementation tableHPlusMSSqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableHPlusMySqlTableInclude tableHPlusMySqlTableIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusMySqlTableMembers tableHPlusMySqlTableMembersHandler = null;
	private CFBamSaxLoaderTableHPlusMySqlTableImplementation tableHPlusMySqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableHPlusOracleTableInclude tableHPlusOracleTableIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusOracleTableMembers tableHPlusOracleTableMembersHandler = null;
	private CFBamSaxLoaderTableHPlusOracleTableImplementation tableHPlusOracleTableImplementationHandler = null;
	private CFBamSaxLoaderTableHPlusPgSqlTableInclude tableHPlusPgSqlTableIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusPgSqlTableMembers tableHPlusPgSqlTableMembersHandler = null;
	private CFBamSaxLoaderTableHPlusPgSqlTableImplementation tableHPlusPgSqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableHPlusRamTableInclude tableHPlusRamTableIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusRamTableMembers tableHPlusRamTableMembersHandler = null;
	private CFBamSaxLoaderTableHPlusRamTableImplementation tableHPlusRamTableImplementationHandler = null;
	private CFBamSaxLoaderTableHPlusSaxLoaderInclude tableHPlusSaxLoaderIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusSaxLoaderStartElement tableHPlusSaxLoaderStartElementHandler = null;
	private CFBamSaxLoaderTableHPlusSaxLoaderEndElement tableHPlusSaxLoaderEndElementHandler = null;
	private CFBamSaxLoaderTableHPlusXMsgTableInclude tableHPlusXMsgTableIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusXMsgTableFormatters tableHPlusXMsgTableFormattersHandler = null;
	private CFBamSaxLoaderTableHPlusXMsgRqstTableInclude tableHPlusXMsgRqstTableIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusXMsgRspnTableInclude tableHPlusXMsgRspnTableIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusXMsgClientTableInclude tableHPlusXMsgClientTableIncludeHandler = null;
	private CFBamSaxLoaderTableHPlusXMsgRqstTableBody tableHPlusXMsgRqstTableBodyHandler = null;
	private CFBamSaxLoaderTableHPlusXMsgRspnTableBody tableHPlusXMsgRspnTableBodyHandler = null;
	private CFBamSaxLoaderTableHPlusXMsgClientTableBody tableHPlusXMsgClientTableBodyHandler = null;
	private CFBamSaxLoaderTableCSharpObjMembers tableCSharpObjMembersHandler = null;
	private CFBamSaxLoaderTableCSharpObjInterface tableCSharpObjInterfaceHandler = null;
	private CFBamSaxLoaderTableCSharpObjUsing tableCSharpObjUsingHandler = null;
	private CFBamSaxLoaderTableCSharpObjImplementation tableCSharpObjImplementationHandler = null;
	private CFBamSaxLoaderTableCSharpEditObjMembers tableCSharpEditObjMembersHandler = null;
	private CFBamSaxLoaderTableCSharpEditObjInterface tableCSharpEditObjInterfaceHandler = null;
	private CFBamSaxLoaderTableCSharpEditObjUsing tableCSharpEditObjUsingHandler = null;
	private CFBamSaxLoaderTableCSharpEditObjImplementation tableCSharpEditObjImplementationHandler = null;
	private CFBamSaxLoaderTableCSharpTableUsing tableCSharpTableUsingHandler = null;
	private CFBamSaxLoaderTableCSharpTableMembers tableCSharpTableMembersHandler = null;
	private CFBamSaxLoaderTableCSharpTableInterface tableCSharpTableInterfaceHandler = null;
	private CFBamSaxLoaderTableCSharpTableImplementation tableCSharpTableImplementationHandler = null;
	private CFBamSaxLoaderTableCSharpTableObjUsing tableCSharpTableObjUsingHandler = null;
	private CFBamSaxLoaderTableCSharpTableObjMembers tableCSharpTableObjMembersHandler = null;
	private CFBamSaxLoaderTableCSharpTableObjInterface tableCSharpTableObjInterfaceHandler = null;
	private CFBamSaxLoaderTableCSharpTableObjImplementation tableCSharpTableObjImplementationHandler = null;
	private CFBamSaxLoaderTableCSharpDb2LUWTableUsing tableCSharpDb2LUWTableUsingHandler = null;
	private CFBamSaxLoaderTableCSharpDb2LUWTableMembers tableCSharpDb2LUWTableMembersHandler = null;
	private CFBamSaxLoaderTableCSharpDb2LUWTableImplementation tableCSharpDb2LUWTableImplementationHandler = null;
	private CFBamSaxLoaderTableCSharpMSSqlTableUsing tableCSharpMSSqlTableUsingHandler = null;
	private CFBamSaxLoaderTableCSharpMSSqlTableMembers tableCSharpMSSqlTableMembersHandler = null;
	private CFBamSaxLoaderTableCSharpMSSqlTableImplementation tableCSharpMSSqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableCSharpMySqlTableUsing tableCSharpMySqlTableUsingHandler = null;
	private CFBamSaxLoaderTableCSharpMySqlTableMembers tableCSharpMySqlTableMembersHandler = null;
	private CFBamSaxLoaderTableCSharpMySqlTableImplementation tableCSharpMySqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableCSharpOracleTableUsing tableCSharpOracleTableUsingHandler = null;
	private CFBamSaxLoaderTableCSharpOracleTableMembers tableCSharpOracleTableMembersHandler = null;
	private CFBamSaxLoaderTableCSharpOracleTableImplementation tableCSharpOracleTableImplementationHandler = null;
	private CFBamSaxLoaderTableCSharpPgSqlTableUsing tableCSharpPgSqlTableUsingHandler = null;
	private CFBamSaxLoaderTableCSharpPgSqlTableMembers tableCSharpPgSqlTableMembersHandler = null;
	private CFBamSaxLoaderTableCSharpPgSqlTableImplementation tableCSharpPgSqlTableImplementationHandler = null;
	private CFBamSaxLoaderTableCSharpRamTableUsing tableCSharpRamTableUsingHandler = null;
	private CFBamSaxLoaderTableCSharpRamTableMembers tableCSharpRamTableMembersHandler = null;
	private CFBamSaxLoaderTableCSharpRamTableImplementation tableCSharpRamTableImplementationHandler = null;
	private CFBamSaxLoaderTableCSharpSaxLoaderUsing tableCSharpSaxLoaderUsingHandler = null;
	private CFBamSaxLoaderTableCSharpSaxLoaderStartElement tableCSharpSaxLoaderStartElementHandler = null;
	private CFBamSaxLoaderTableCSharpSaxLoaderEndElement tableCSharpSaxLoaderEndElementHandler = null;
	private CFBamSaxLoaderTableCSharpXMsgTableUsing tableCSharpXMsgTableUsingHandler = null;
	private CFBamSaxLoaderTableCSharpXMsgTableFormatters tableCSharpXMsgTableFormattersHandler = null;
	private CFBamSaxLoaderTableCSharpXMsgRqstTableUsing tableCSharpXMsgRqstTableUsingHandler = null;
	private CFBamSaxLoaderTableCSharpXMsgRspnTableUsing tableCSharpXMsgRspnTableUsingHandler = null;
	private CFBamSaxLoaderTableCSharpXMsgClientTableUsing tableCSharpXMsgClientTableUsingHandler = null;
	private CFBamSaxLoaderTableCSharpXMsgRqstTableBody tableCSharpXMsgRqstTableBodyHandler = null;
	private CFBamSaxLoaderTableCSharpXMsgRspnTableBody tableCSharpXMsgRspnTableBodyHandler = null;
	private CFBamSaxLoaderTableCSharpXMsgClientTableBody tableCSharpXMsgClientTableBodyHandler = null;
	private CFBamSaxLoaderTableCol tableColHandler = null;
	private CFBamSaxLoaderTenant tenantHandler = null;
	private CFBamSaxLoaderTextCol textColHandler = null;
	private CFBamSaxLoaderTextDef textDefHandler = null;
	private CFBamSaxLoaderTextType textTypeHandler = null;
	private CFBamSaxLoaderTimeCol timeColHandler = null;
	private CFBamSaxLoaderTimeDef timeDefHandler = null;
	private CFBamSaxLoaderTimeType timeTypeHandler = null;
	private CFBamSaxLoaderTimestampCol timestampColHandler = null;
	private CFBamSaxLoaderTimestampDef timestampDefHandler = null;
	private CFBamSaxLoaderTimestampType timestampTypeHandler = null;
	private CFBamSaxLoaderTld tldHandler = null;
	private CFBamSaxLoaderTokenCol tokenColHandler = null;
	private CFBamSaxLoaderTokenDef tokenDefHandler = null;
	private CFBamSaxLoaderTokenType tokenTypeHandler = null;
	private CFBamSaxLoaderTopDomain topDomainHandler = null;
	private CFBamSaxLoaderTopProject topProjectHandler = null;
	private CFBamSaxLoaderUInt16Col uInt16ColHandler = null;
	private CFBamSaxLoaderUInt16Def uInt16DefHandler = null;
	private CFBamSaxLoaderUInt16Type uInt16TypeHandler = null;
	private CFBamSaxLoaderUInt32Col uInt32ColHandler = null;
	private CFBamSaxLoaderUInt32Def uInt32DefHandler = null;
	private CFBamSaxLoaderUInt32Type uInt32TypeHandler = null;
	private CFBamSaxLoaderUInt64Col uInt64ColHandler = null;
	private CFBamSaxLoaderUInt64Def uInt64DefHandler = null;
	private CFBamSaxLoaderUInt64Type uInt64TypeHandler = null;
	private CFBamSaxLoaderURLProtocol uRLProtocolHandler = null;
	private CFBamSaxLoaderUuidCol uuidColHandler = null;
	private CFBamSaxLoaderUuidDef uuidDefHandler = null;
	private CFBamSaxLoaderUuidGen uuidGenHandler = null;
	private CFBamSaxLoaderUuidType uuidTypeHandler = null;
	private CFBamSaxLoaderValue valueHandler = null;
	private CFBamSaxRootHandler saxRootHandler = null;

	private CFBamSaxDocHandler saxDocHandler = null;

	// Schema object accessors

	// SchemaObj accessors

	public ICFBamSchemaObj getSchemaObj() {
		return( schemaObj );
	}

	public void setSchemaObj( ICFBamSchemaObj value ) {
		schemaObj = value;
	}

	// Element Handler Resolver Factories

	protected CFBamSaxLoaderAtom getAtomHandler() {
		if( atomHandler == null ) {
			atomHandler = new CFBamSaxLoaderAtom( this );
		}
		return( atomHandler );
	}
	protected CFBamSaxLoaderBlobCol getBlobColHandler() {
		if( blobColHandler == null ) {
			blobColHandler = new CFBamSaxLoaderBlobCol( this );
		}
		return( blobColHandler );
	}
	protected CFBamSaxLoaderBlobDef getBlobDefHandler() {
		if( blobDefHandler == null ) {
			blobDefHandler = new CFBamSaxLoaderBlobDef( this );
		}
		return( blobDefHandler );
	}
	protected CFBamSaxLoaderBlobType getBlobTypeHandler() {
		if( blobTypeHandler == null ) {
			blobTypeHandler = new CFBamSaxLoaderBlobType( this );
		}
		return( blobTypeHandler );
	}
	protected CFBamSaxLoaderBoolCol getBoolColHandler() {
		if( boolColHandler == null ) {
			boolColHandler = new CFBamSaxLoaderBoolCol( this );
		}
		return( boolColHandler );
	}
	protected CFBamSaxLoaderBoolDef getBoolDefHandler() {
		if( boolDefHandler == null ) {
			boolDefHandler = new CFBamSaxLoaderBoolDef( this );
		}
		return( boolDefHandler );
	}
	protected CFBamSaxLoaderBoolType getBoolTypeHandler() {
		if( boolTypeHandler == null ) {
			boolTypeHandler = new CFBamSaxLoaderBoolType( this );
		}
		return( boolTypeHandler );
	}
	protected CFBamSaxLoaderChain getChainHandler() {
		if( chainHandler == null ) {
			chainHandler = new CFBamSaxLoaderChain( this );
		}
		return( chainHandler );
	}
	protected CFBamSaxLoaderClearDep getClearDepHandler() {
		if( clearDepHandler == null ) {
			clearDepHandler = new CFBamSaxLoaderClearDep( this );
		}
		return( clearDepHandler );
	}
	protected CFBamSaxLoaderClearSubDep1 getClearSubDep1Handler() {
		if( clearSubDep1Handler == null ) {
			clearSubDep1Handler = new CFBamSaxLoaderClearSubDep1( this );
			clearSubDep1Handler.addElementHandler( "ClearSubDep2", getClearSubDep2Handler() );
		}
		return( clearSubDep1Handler );
	}
	protected CFBamSaxLoaderClearSubDep2 getClearSubDep2Handler() {
		if( clearSubDep2Handler == null ) {
			clearSubDep2Handler = new CFBamSaxLoaderClearSubDep2( this );
			clearSubDep2Handler.addElementHandler( "ClearSubDep3", getClearSubDep3Handler() );
		}
		return( clearSubDep2Handler );
	}
	protected CFBamSaxLoaderClearSubDep3 getClearSubDep3Handler() {
		if( clearSubDep3Handler == null ) {
			clearSubDep3Handler = new CFBamSaxLoaderClearSubDep3( this );
		}
		return( clearSubDep3Handler );
	}
	protected CFBamSaxLoaderClearTopDep getClearTopDepHandler() {
		if( clearTopDepHandler == null ) {
			clearTopDepHandler = new CFBamSaxLoaderClearTopDep( this );
			clearTopDepHandler.addElementHandler( "ClearSubDep1", getClearSubDep1Handler() );
		}
		return( clearTopDepHandler );
	}
	protected CFBamSaxLoaderCluster getClusterHandler() {
		if( clusterHandler == null ) {
			clusterHandler = new CFBamSaxLoaderCluster( this );
			clusterHandler.addElementHandler( "HostNode", getHostNodeHandler() );
			clusterHandler.addElementHandler( "Tenant", getTenantHandler() );
			clusterHandler.addElementHandler( "SecApp", getSecAppHandler() );
			clusterHandler.addElementHandler( "SecGroup", getSecGroupHandler() );
			clusterHandler.addElementHandler( "SysCluster", getSysClusterHandler() );
		}
		return( clusterHandler );
	}
	protected CFBamSaxLoaderDateCol getDateColHandler() {
		if( dateColHandler == null ) {
			dateColHandler = new CFBamSaxLoaderDateCol( this );
		}
		return( dateColHandler );
	}
	protected CFBamSaxLoaderDateDef getDateDefHandler() {
		if( dateDefHandler == null ) {
			dateDefHandler = new CFBamSaxLoaderDateDef( this );
		}
		return( dateDefHandler );
	}
	protected CFBamSaxLoaderDateType getDateTypeHandler() {
		if( dateTypeHandler == null ) {
			dateTypeHandler = new CFBamSaxLoaderDateType( this );
		}
		return( dateTypeHandler );
	}
	protected CFBamSaxLoaderDelDep getDelDepHandler() {
		if( delDepHandler == null ) {
			delDepHandler = new CFBamSaxLoaderDelDep( this );
		}
		return( delDepHandler );
	}
	protected CFBamSaxLoaderDelSubDep1 getDelSubDep1Handler() {
		if( delSubDep1Handler == null ) {
			delSubDep1Handler = new CFBamSaxLoaderDelSubDep1( this );
			delSubDep1Handler.addElementHandler( "DelSubDep2", getDelSubDep2Handler() );
		}
		return( delSubDep1Handler );
	}
	protected CFBamSaxLoaderDelSubDep2 getDelSubDep2Handler() {
		if( delSubDep2Handler == null ) {
			delSubDep2Handler = new CFBamSaxLoaderDelSubDep2( this );
			delSubDep2Handler.addElementHandler( "DelSubDep3", getDelSubDep3Handler() );
		}
		return( delSubDep2Handler );
	}
	protected CFBamSaxLoaderDelSubDep3 getDelSubDep3Handler() {
		if( delSubDep3Handler == null ) {
			delSubDep3Handler = new CFBamSaxLoaderDelSubDep3( this );
		}
		return( delSubDep3Handler );
	}
	protected CFBamSaxLoaderDelTopDep getDelTopDepHandler() {
		if( delTopDepHandler == null ) {
			delTopDepHandler = new CFBamSaxLoaderDelTopDep( this );
			delTopDepHandler.addElementHandler( "DelSubDep1", getDelSubDep1Handler() );
		}
		return( delTopDepHandler );
	}
	protected CFBamSaxLoaderDoubleCol getDoubleColHandler() {
		if( doubleColHandler == null ) {
			doubleColHandler = new CFBamSaxLoaderDoubleCol( this );
		}
		return( doubleColHandler );
	}
	protected CFBamSaxLoaderDoubleDef getDoubleDefHandler() {
		if( doubleDefHandler == null ) {
			doubleDefHandler = new CFBamSaxLoaderDoubleDef( this );
		}
		return( doubleDefHandler );
	}
	protected CFBamSaxLoaderDoubleType getDoubleTypeHandler() {
		if( doubleTypeHandler == null ) {
			doubleTypeHandler = new CFBamSaxLoaderDoubleType( this );
		}
		return( doubleTypeHandler );
	}
	protected CFBamSaxLoaderEnumDef getEnumDefHandler() {
		if( enumDefHandler == null ) {
			enumDefHandler = new CFBamSaxLoaderEnumDef( this );
			enumDefHandler.addElementHandler( "EnumTag", getEnumTagHandler() );
		}
		return( enumDefHandler );
	}
	protected CFBamSaxLoaderEnumTag getEnumTagHandler() {
		if( enumTagHandler == null ) {
			enumTagHandler = new CFBamSaxLoaderEnumTag( this );
		}
		return( enumTagHandler );
	}
	protected CFBamSaxLoaderEnumType getEnumTypeHandler() {
		if( enumTypeHandler == null ) {
			enumTypeHandler = new CFBamSaxLoaderEnumType( this );
			enumTypeHandler.addElementHandler( "EnumTag", getEnumTagHandler() );
		}
		return( enumTypeHandler );
	}
	protected CFBamSaxLoaderFloatCol getFloatColHandler() {
		if( floatColHandler == null ) {
			floatColHandler = new CFBamSaxLoaderFloatCol( this );
		}
		return( floatColHandler );
	}
	protected CFBamSaxLoaderFloatDef getFloatDefHandler() {
		if( floatDefHandler == null ) {
			floatDefHandler = new CFBamSaxLoaderFloatDef( this );
		}
		return( floatDefHandler );
	}
	protected CFBamSaxLoaderFloatType getFloatTypeHandler() {
		if( floatTypeHandler == null ) {
			floatTypeHandler = new CFBamSaxLoaderFloatType( this );
		}
		return( floatTypeHandler );
	}
	protected CFBamSaxLoaderHostNode getHostNodeHandler() {
		if( hostNodeHandler == null ) {
			hostNodeHandler = new CFBamSaxLoaderHostNode( this );
			hostNodeHandler.addElementHandler( "Service", getServiceHandler() );
		}
		return( hostNodeHandler );
	}
	protected CFBamSaxLoaderISOCcy getISOCcyHandler() {
		if( iSOCcyHandler == null ) {
			iSOCcyHandler = new CFBamSaxLoaderISOCcy( this );
		}
		return( iSOCcyHandler );
	}
	protected CFBamSaxLoaderISOCtry getISOCtryHandler() {
		if( iSOCtryHandler == null ) {
			iSOCtryHandler = new CFBamSaxLoaderISOCtry( this );
			iSOCtryHandler.addElementHandler( "ISOCtryCcy", getISOCtryCcyHandler() );
			iSOCtryHandler.addElementHandler( "ISOCtryLang", getISOCtryLangHandler() );
		}
		return( iSOCtryHandler );
	}
	protected CFBamSaxLoaderISOCtryCcy getISOCtryCcyHandler() {
		if( iSOCtryCcyHandler == null ) {
			iSOCtryCcyHandler = new CFBamSaxLoaderISOCtryCcy( this );
		}
		return( iSOCtryCcyHandler );
	}
	protected CFBamSaxLoaderISOCtryLang getISOCtryLangHandler() {
		if( iSOCtryLangHandler == null ) {
			iSOCtryLangHandler = new CFBamSaxLoaderISOCtryLang( this );
		}
		return( iSOCtryLangHandler );
	}
	protected CFBamSaxLoaderISOLang getISOLangHandler() {
		if( iSOLangHandler == null ) {
			iSOLangHandler = new CFBamSaxLoaderISOLang( this );
		}
		return( iSOLangHandler );
	}
	protected CFBamSaxLoaderISOTZone getISOTZoneHandler() {
		if( iSOTZoneHandler == null ) {
			iSOTZoneHandler = new CFBamSaxLoaderISOTZone( this );
		}
		return( iSOTZoneHandler );
	}
	protected CFBamSaxLoaderId16Gen getId16GenHandler() {
		if( id16GenHandler == null ) {
			id16GenHandler = new CFBamSaxLoaderId16Gen( this );
		}
		return( id16GenHandler );
	}
	protected CFBamSaxLoaderId32Gen getId32GenHandler() {
		if( id32GenHandler == null ) {
			id32GenHandler = new CFBamSaxLoaderId32Gen( this );
		}
		return( id32GenHandler );
	}
	protected CFBamSaxLoaderId64Gen getId64GenHandler() {
		if( id64GenHandler == null ) {
			id64GenHandler = new CFBamSaxLoaderId64Gen( this );
		}
		return( id64GenHandler );
	}
	protected CFBamSaxLoaderIndex getIndexHandler() {
		if( indexHandler == null ) {
			indexHandler = new CFBamSaxLoaderIndex( this );
			indexHandler.addElementHandler( "IndexCol", getIndexColHandler() );
		}
		return( indexHandler );
	}
	protected CFBamSaxLoaderIndexCol getIndexColHandler() {
		if( indexColHandler == null ) {
			indexColHandler = new CFBamSaxLoaderIndexCol( this );
		}
		return( indexColHandler );
	}
	protected CFBamSaxLoaderInt16Col getInt16ColHandler() {
		if( int16ColHandler == null ) {
			int16ColHandler = new CFBamSaxLoaderInt16Col( this );
		}
		return( int16ColHandler );
	}
	protected CFBamSaxLoaderInt16Def getInt16DefHandler() {
		if( int16DefHandler == null ) {
			int16DefHandler = new CFBamSaxLoaderInt16Def( this );
		}
		return( int16DefHandler );
	}
	protected CFBamSaxLoaderInt16Type getInt16TypeHandler() {
		if( int16TypeHandler == null ) {
			int16TypeHandler = new CFBamSaxLoaderInt16Type( this );
		}
		return( int16TypeHandler );
	}
	protected CFBamSaxLoaderInt32Col getInt32ColHandler() {
		if( int32ColHandler == null ) {
			int32ColHandler = new CFBamSaxLoaderInt32Col( this );
		}
		return( int32ColHandler );
	}
	protected CFBamSaxLoaderInt32Def getInt32DefHandler() {
		if( int32DefHandler == null ) {
			int32DefHandler = new CFBamSaxLoaderInt32Def( this );
		}
		return( int32DefHandler );
	}
	protected CFBamSaxLoaderInt32Type getInt32TypeHandler() {
		if( int32TypeHandler == null ) {
			int32TypeHandler = new CFBamSaxLoaderInt32Type( this );
		}
		return( int32TypeHandler );
	}
	protected CFBamSaxLoaderInt64Col getInt64ColHandler() {
		if( int64ColHandler == null ) {
			int64ColHandler = new CFBamSaxLoaderInt64Col( this );
		}
		return( int64ColHandler );
	}
	protected CFBamSaxLoaderInt64Def getInt64DefHandler() {
		if( int64DefHandler == null ) {
			int64DefHandler = new CFBamSaxLoaderInt64Def( this );
		}
		return( int64DefHandler );
	}
	protected CFBamSaxLoaderInt64Type getInt64TypeHandler() {
		if( int64TypeHandler == null ) {
			int64TypeHandler = new CFBamSaxLoaderInt64Type( this );
		}
		return( int64TypeHandler );
	}
	protected CFBamSaxLoaderLicense getLicenseHandler() {
		if( licenseHandler == null ) {
			licenseHandler = new CFBamSaxLoaderLicense( this );
		}
		return( licenseHandler );
	}
	protected CFBamSaxLoaderMajorVersion getMajorVersionHandler() {
		if( majorVersionHandler == null ) {
			majorVersionHandler = new CFBamSaxLoaderMajorVersion( this );
			majorVersionHandler.addElementHandler( "MinorVersion", getMinorVersionHandler() );
		}
		return( majorVersionHandler );
	}
	protected CFBamSaxLoaderMimeType getMimeTypeHandler() {
		if( mimeTypeHandler == null ) {
			mimeTypeHandler = new CFBamSaxLoaderMimeType( this );
		}
		return( mimeTypeHandler );
	}
	protected CFBamSaxLoaderMinorVersion getMinorVersionHandler() {
		if( minorVersionHandler == null ) {
			minorVersionHandler = new CFBamSaxLoaderMinorVersion( this );
			minorVersionHandler.addElementHandler( "SchemaDef", getSchemaDefHandler() );
		}
		return( minorVersionHandler );
	}
	protected CFBamSaxLoaderNmTokenCol getNmTokenColHandler() {
		if( nmTokenColHandler == null ) {
			nmTokenColHandler = new CFBamSaxLoaderNmTokenCol( this );
		}
		return( nmTokenColHandler );
	}
	protected CFBamSaxLoaderNmTokenDef getNmTokenDefHandler() {
		if( nmTokenDefHandler == null ) {
			nmTokenDefHandler = new CFBamSaxLoaderNmTokenDef( this );
		}
		return( nmTokenDefHandler );
	}
	protected CFBamSaxLoaderNmTokenType getNmTokenTypeHandler() {
		if( nmTokenTypeHandler == null ) {
			nmTokenTypeHandler = new CFBamSaxLoaderNmTokenType( this );
		}
		return( nmTokenTypeHandler );
	}
	protected CFBamSaxLoaderNmTokensCol getNmTokensColHandler() {
		if( nmTokensColHandler == null ) {
			nmTokensColHandler = new CFBamSaxLoaderNmTokensCol( this );
		}
		return( nmTokensColHandler );
	}
	protected CFBamSaxLoaderNmTokensDef getNmTokensDefHandler() {
		if( nmTokensDefHandler == null ) {
			nmTokensDefHandler = new CFBamSaxLoaderNmTokensDef( this );
		}
		return( nmTokensDefHandler );
	}
	protected CFBamSaxLoaderNmTokensType getNmTokensTypeHandler() {
		if( nmTokensTypeHandler == null ) {
			nmTokensTypeHandler = new CFBamSaxLoaderNmTokensType( this );
		}
		return( nmTokensTypeHandler );
	}
	protected CFBamSaxLoaderNumberCol getNumberColHandler() {
		if( numberColHandler == null ) {
			numberColHandler = new CFBamSaxLoaderNumberCol( this );
		}
		return( numberColHandler );
	}
	protected CFBamSaxLoaderNumberDef getNumberDefHandler() {
		if( numberDefHandler == null ) {
			numberDefHandler = new CFBamSaxLoaderNumberDef( this );
		}
		return( numberDefHandler );
	}
	protected CFBamSaxLoaderNumberType getNumberTypeHandler() {
		if( numberTypeHandler == null ) {
			numberTypeHandler = new CFBamSaxLoaderNumberType( this );
		}
		return( numberTypeHandler );
	}
	protected CFBamSaxLoaderParam getParamHandler() {
		if( paramHandler == null ) {
			paramHandler = new CFBamSaxLoaderParam( this );
		}
		return( paramHandler );
	}
	protected CFBamSaxLoaderPopDep getPopDepHandler() {
		if( popDepHandler == null ) {
			popDepHandler = new CFBamSaxLoaderPopDep( this );
		}
		return( popDepHandler );
	}
	protected CFBamSaxLoaderPopSubDep1 getPopSubDep1Handler() {
		if( popSubDep1Handler == null ) {
			popSubDep1Handler = new CFBamSaxLoaderPopSubDep1( this );
			popSubDep1Handler.addElementHandler( "PopSubDep2", getPopSubDep2Handler() );
		}
		return( popSubDep1Handler );
	}
	protected CFBamSaxLoaderPopSubDep2 getPopSubDep2Handler() {
		if( popSubDep2Handler == null ) {
			popSubDep2Handler = new CFBamSaxLoaderPopSubDep2( this );
			popSubDep2Handler.addElementHandler( "PopSubDep3", getPopSubDep3Handler() );
		}
		return( popSubDep2Handler );
	}
	protected CFBamSaxLoaderPopSubDep3 getPopSubDep3Handler() {
		if( popSubDep3Handler == null ) {
			popSubDep3Handler = new CFBamSaxLoaderPopSubDep3( this );
		}
		return( popSubDep3Handler );
	}
	protected CFBamSaxLoaderPopTopDep getPopTopDepHandler() {
		if( popTopDepHandler == null ) {
			popTopDepHandler = new CFBamSaxLoaderPopTopDep( this );
			popTopDepHandler.addElementHandler( "PopSubDep1", getPopSubDep1Handler() );
		}
		return( popTopDepHandler );
	}
	protected CFBamSaxLoaderRelation getRelationHandler() {
		if( relationHandler == null ) {
			relationHandler = new CFBamSaxLoaderRelation( this );
			relationHandler.addElementHandler( "RelationCol", getRelationColHandler() );
			relationHandler.addElementHandler( "PopTopDep", getPopTopDepHandler() );
		}
		return( relationHandler );
	}
	protected CFBamSaxLoaderRelationCol getRelationColHandler() {
		if( relationColHandler == null ) {
			relationColHandler = new CFBamSaxLoaderRelationCol( this );
		}
		return( relationColHandler );
	}
	protected CFBamSaxLoaderSchemaDef getSchemaDefHandler() {
		if( schemaDefHandler == null ) {
			schemaDefHandler = new CFBamSaxLoaderSchemaDef( this );
			schemaDefHandler.addElementHandler( "Table", getTableHandler() );
			schemaDefHandler.addElementHandler( "BlobType", getBlobTypeHandler() );
			schemaDefHandler.addElementHandler( "BoolType", getBoolTypeHandler() );
			schemaDefHandler.addElementHandler( "DateType", getDateTypeHandler() );
			schemaDefHandler.addElementHandler( "DoubleType", getDoubleTypeHandler() );
			schemaDefHandler.addElementHandler( "FloatType", getFloatTypeHandler() );
			schemaDefHandler.addElementHandler( "Int16Type", getInt16TypeHandler() );
			schemaDefHandler.addElementHandler( "Id16Gen", getId16GenHandler() );
			schemaDefHandler.addElementHandler( "EnumType", getEnumTypeHandler() );
			schemaDefHandler.addElementHandler( "Int32Type", getInt32TypeHandler() );
			schemaDefHandler.addElementHandler( "Id32Gen", getId32GenHandler() );
			schemaDefHandler.addElementHandler( "Int64Type", getInt64TypeHandler() );
			schemaDefHandler.addElementHandler( "Id64Gen", getId64GenHandler() );
			schemaDefHandler.addElementHandler( "NmTokenType", getNmTokenTypeHandler() );
			schemaDefHandler.addElementHandler( "NmTokensType", getNmTokensTypeHandler() );
			schemaDefHandler.addElementHandler( "NumberType", getNumberTypeHandler() );
			schemaDefHandler.addElementHandler( "StringType", getStringTypeHandler() );
			schemaDefHandler.addElementHandler( "TZDateType", getTZDateTypeHandler() );
			schemaDefHandler.addElementHandler( "TZTimeType", getTZTimeTypeHandler() );
			schemaDefHandler.addElementHandler( "TZTimestampType", getTZTimestampTypeHandler() );
			schemaDefHandler.addElementHandler( "TextType", getTextTypeHandler() );
			schemaDefHandler.addElementHandler( "TimeType", getTimeTypeHandler() );
			schemaDefHandler.addElementHandler( "TimestampType", getTimestampTypeHandler() );
			schemaDefHandler.addElementHandler( "TokenType", getTokenTypeHandler() );
			schemaDefHandler.addElementHandler( "UInt16Type", getUInt16TypeHandler() );
			schemaDefHandler.addElementHandler( "UInt32Type", getUInt32TypeHandler() );
			schemaDefHandler.addElementHandler( "UInt64Type", getUInt64TypeHandler() );
			schemaDefHandler.addElementHandler( "UuidType", getUuidTypeHandler() );
			schemaDefHandler.addElementHandler( "UuidGen", getUuidGenHandler() );
			schemaDefHandler.addElementHandler( "SchemaRef", getSchemaRefHandler() );
			schemaDefHandler.addElementHandler( "CafeSchemaObjImport", getSchemaDefCafeSchemaObjImportHandler() );
			schemaDefHandler.addElementHandler( "CafeSchemaObjInterface", getSchemaDefCafeSchemaObjInterfaceHandler() );
			schemaDefHandler.addElementHandler( "CafeSchemaObjMembers", getSchemaDefCafeSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CafeSchemaObjImplementation", getSchemaDefCafeSchemaObjImplementationHandler() );
			schemaDefHandler.addElementHandler( "CafeDb2LUWSchemaObjMembers", getSchemaDefCafeDb2LUWSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CafeDb2LUWSchemaObjImpl", getSchemaDefCafeDb2LUWSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CafeDb2LUWSchemaObjImport", getSchemaDefCafeDb2LUWSchemaObjImportHandler() );
			schemaDefHandler.addElementHandler( "CafeMSSqlSchemaObjMembers", getSchemaDefCafeMSSqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CafeMSSqlSchemaObjImpl", getSchemaDefCafeMSSqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CafeMSSqlSchemaObjImport", getSchemaDefCafeMSSqlSchemaObjImportHandler() );
			schemaDefHandler.addElementHandler( "CafeMySqlSchemaObjMembers", getSchemaDefCafeMySqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CafeMySqlSchemaObjImpl", getSchemaDefCafeMySqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CafeMySqlSchemaObjImport", getSchemaDefCafeMySqlSchemaObjImportHandler() );
			schemaDefHandler.addElementHandler( "CafeOracleSchemaObjMembers", getSchemaDefCafeOracleSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CafeOracleSchemaObjImpl", getSchemaDefCafeOracleSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CafeOracleSchemaObjImport", getSchemaDefCafeOracleSchemaObjImportHandler() );
			schemaDefHandler.addElementHandler( "CafePgSqlSchemaObjMembers", getSchemaDefCafePgSqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CafePgSqlSchemaObjImpl", getSchemaDefCafePgSqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CafePgSqlSchemaObjImport", getSchemaDefCafePgSqlSchemaObjImportHandler() );
			schemaDefHandler.addElementHandler( "CafeRamSchemaObjMembers", getSchemaDefCafeRamSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CafeRamSchemaObjImpl", getSchemaDefCafeRamSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CafeRamSchemaObjImport", getSchemaDefCafeRamSchemaObjImportHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgSchemaImport", getSchemaDefCafeXMsgSchemaImportHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgSchemaFormatters", getSchemaDefCafeXMsgSchemaFormattersHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgClientSchemaImport", getSchemaDefCafeXMsgClientSchemaImportHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgClientSchemaBody", getSchemaDefCafeXMsgClientSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgRqstSchemaBody", getSchemaDefCafeXMsgRqstSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgRqstSchemaImport", getSchemaDefCafeXMsgRqstSchemaImportHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgRqstSchemaWireParsers", getSchemaDefCafeXMsgRqstSchemaWireParsersHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgRqstSchemaXsdSpec", getSchemaDefCafeXMsgRqstSchemaXsdSpecHandler() );
			schemaDefHandler.addElementHandler( "CafeSchemaXsdElementList", getSchemaDefCafeSchemaXsdElementListHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgRspnSchemaBody", getSchemaDefCafeXMsgRspnSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgRspnSchemaImport", getSchemaDefCafeXMsgRspnSchemaImportHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgRspnSchemaWireParsers", getSchemaDefCafeXMsgRspnSchemaWireParsersHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgRspnSchemaXsdElementList", getSchemaDefCafeXMsgRspnSchemaXsdElementListHandler() );
			schemaDefHandler.addElementHandler( "CafeXMsgRspnSchemaXsdSpec", getSchemaDefCafeXMsgRspnSchemaXsdSpecHandler() );
			schemaDefHandler.addElementHandler( "CPlusSchemaObjInclude", getSchemaDefCPlusSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "CPlusSchemaObjInterface", getSchemaDefCPlusSchemaObjInterfaceHandler() );
			schemaDefHandler.addElementHandler( "CPlusSchemaObjMembers", getSchemaDefCPlusSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CPlusSchemaObjImplementation", getSchemaDefCPlusSchemaObjImplementationHandler() );
			schemaDefHandler.addElementHandler( "CPlusDb2LUWSchemaObjMembers", getSchemaDefCPlusDb2LUWSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CPlusDb2LUWSchemaObjImpl", getSchemaDefCPlusDb2LUWSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CPlusDb2LUWSchemaObjInclude", getSchemaDefCPlusDb2LUWSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "CPlusMSSqlSchemaObjMembers", getSchemaDefCPlusMSSqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CPlusMSSqlSchemaObjImpl", getSchemaDefCPlusMSSqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CPlusMSSqlSchemaObjInclude", getSchemaDefCPlusMSSqlSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "CPlusMySqlSchemaObjMembers", getSchemaDefCPlusMySqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CPlusMySqlSchemaObjImpl", getSchemaDefCPlusMySqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CPlusMySqlSchemaObjInclude", getSchemaDefCPlusMySqlSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "CPlusOracleSchemaObjMembers", getSchemaDefCPlusOracleSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CPlusOracleSchemaObjImpl", getSchemaDefCPlusOracleSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CPlusOracleSchemaObjInclude", getSchemaDefCPlusOracleSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "CPlusPgSqlSchemaObjMembers", getSchemaDefCPlusPgSqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CPlusPgSqlSchemaObjImpl", getSchemaDefCPlusPgSqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CPlusPgSqlSchemaObjInclude", getSchemaDefCPlusPgSqlSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "CPlusRamSchemaObjMembers", getSchemaDefCPlusRamSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CPlusRamSchemaObjImpl", getSchemaDefCPlusRamSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CPlusRamSchemaObjInclude", getSchemaDefCPlusRamSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgSchemaInclude", getSchemaDefCPlusXMsgSchemaIncludeHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgSchemaFormatters", getSchemaDefCPlusXMsgSchemaFormattersHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgClientSchemaInclude", getSchemaDefCPlusXMsgClientSchemaIncludeHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgClientSchemaBody", getSchemaDefCPlusXMsgClientSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgRqstSchemaBody", getSchemaDefCPlusXMsgRqstSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgRqstSchemaInclude", getSchemaDefCPlusXMsgRqstSchemaIncludeHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgRqstSchemaWireParsers", getSchemaDefCPlusXMsgRqstSchemaWireParsersHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgRqstSchemaXsdSpec", getSchemaDefCPlusXMsgRqstSchemaXsdSpecHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgRqstSchemaXsdElementList", getSchemaDefCPlusXMsgRqstSchemaXsdElementListHandler() );
			schemaDefHandler.addElementHandler( "CPlusXmsgRspnSchemaBody", getSchemaDefCPlusXmsgRspnSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgRspnSchemaInclude", getSchemaDefCPlusXMsgRspnSchemaIncludeHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgRspnSchemaWireParsers", getSchemaDefCPlusXMsgRspnSchemaWireParsersHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgRspnSchemaXsdElementList", getSchemaDefCPlusXMsgRspnSchemaXsdElementListHandler() );
			schemaDefHandler.addElementHandler( "CPlusXMsgRspnSchemaXsdSpec", getSchemaDefCPlusXMsgRspnSchemaXsdSpecHandler() );
			schemaDefHandler.addElementHandler( "HPlusSchemaObjInclude", getSchemaDefHPlusSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "HPlusSchemaObjInterface", getSchemaDefHPlusSchemaObjInterfaceHandler() );
			schemaDefHandler.addElementHandler( "HPlusSchemaObjMembers", getSchemaDefHPlusSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "HPlusSchemaObjImplementation", getSchemaDefHPlusSchemaObjImplementationHandler() );
			schemaDefHandler.addElementHandler( "HPlusDb2LUWSchemaObjMembers", getSchemaDefHPlusDb2LUWSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "HPlusDb2LUWSchemaObjImpl", getSchemaDefHPlusDb2LUWSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "HPlusDb2LUWSchemaObjInclude", getSchemaDefHPlusDb2LUWSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "HPlusMSSqlSchemaObjMembers", getSchemaDefHPlusMSSqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "HPlusMSSqlSchemaObjImpl", getSchemaDefHPlusMSSqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "HPlusMSSqlSchemaObjInclude", getSchemaDefHPlusMSSqlSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "HPlusMySqlSchemaObjMembers", getSchemaDefHPlusMySqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "HPlusMySqlSchemaObjImpl", getSchemaDefHPlusMySqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "HPlusMySqlSchemaObjInclude", getSchemaDefHPlusMySqlSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "HPlusOracleSchemaObjMembers", getSchemaDefHPlusOracleSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "HPlusOracleSchemaObjImpl", getSchemaDefHPlusOracleSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "HPlusOracleSchemaObjInclude", getSchemaDefHPlusOracleSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "HPlusPgSqlSchemaObjMembers", getSchemaDefHPlusPgSqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "HPlusPgSqlSchemaObjImpl", getSchemaDefHPlusPgSqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "HPlusPgSqlSchemaObjInclude", getSchemaDefHPlusPgSqlSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "HPlusRamSchemaObjMembers", getSchemaDefHPlusRamSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "HPlusRamSchemaObjImpl", getSchemaDefHPlusRamSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "HPlusRamSchemaObjInclude", getSchemaDefHPlusRamSchemaObjIncludeHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgSchemaInclude", getSchemaDefHPlusXMsgSchemaIncludeHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgSchemaFormatters", getSchemaDefHPlusXMsgSchemaFormattersHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgClientSchemaInclude", getSchemaDefHPlusXMsgClientSchemaIncludeHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgClientSchemaBody", getSchemaDefHPlusXMsgClientSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgRqstSchemaBody", getSchemaDefHPlusXMsgRqstSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgRqstSchemaInclude", getSchemaDefHPlusXMsgRqstSchemaIncludeHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgRqstSchemaWireParsers", getSchemaDefHPlusXMsgRqstSchemaWireParsersHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgRqstSchemaXsdSpec", getSchemaDefHPlusXMsgRqstSchemaXsdSpecHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgRqstSchemaXsdElementList", getSchemaDefHPlusXMsgRqstSchemaXsdElementListHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgRspnSchemaBody", getSchemaDefHPlusXMsgRspnSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgRspnSchemaInclude", getSchemaDefHPlusXMsgRspnSchemaIncludeHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgRspnSchemaWireParsers", getSchemaDefHPlusXMsgRspnSchemaWireParsersHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgRspnSchemaXsdElementList", getSchemaDefHPlusXMsgRspnSchemaXsdElementListHandler() );
			schemaDefHandler.addElementHandler( "HPlusXMsgRspnSchemaXsdSpec", getSchemaDefHPlusXMsgRspnSchemaXsdSpecHandler() );
			schemaDefHandler.addElementHandler( "CSharpSchemaObjUsing", getSchemaDefCSharpSchemaObjUsingHandler() );
			schemaDefHandler.addElementHandler( "CSharpSchemaObjInterface", getSchemaDefCSharpSchemaObjInterfaceHandler() );
			schemaDefHandler.addElementHandler( "CSharpSchemaObjMembers", getSchemaDefCSharpSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CSharpSchemaObjImplementation", getSchemaDefCSharpSchemaObjImplementationHandler() );
			schemaDefHandler.addElementHandler( "CSharpDb2LUWSchemaObjMembers", getSchemaDefCSharpDb2LUWSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CSharpDb2LUWSchemaObjImpl", getSchemaDefCSharpDb2LUWSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CSharpDb2LUWSchemaObjUsing", getSchemaDefCSharpDb2LUWSchemaObjUsingHandler() );
			schemaDefHandler.addElementHandler( "CSharpMSSqlSchemaObjMembers", getSchemaDefCSharpMSSqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CSharpMSSqlSchemaObjImpl", getSchemaDefCSharpMSSqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CSharpMSSqlSchemaObjUsing", getSchemaDefCSharpMSSqlSchemaObjUsingHandler() );
			schemaDefHandler.addElementHandler( "CSharpMySqlSchemaObjMembers", getSchemaDefCSharpMySqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CSharpMySqlSchemaObjImpl", getSchemaDefCSharpMySqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CSharpMySqlSchemaObjUsing", getSchemaDefCSharpMySqlSchemaObjUsingHandler() );
			schemaDefHandler.addElementHandler( "CSharpOracleSchemaObjMembers", getSchemaDefCSharpOracleSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CSharpOracleSchemaObjImpl", getSchemaDefCSharpOracleSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CSharpOracleSchemaObjUsing", getSchemaDefCSharpOracleSchemaObjUsingHandler() );
			schemaDefHandler.addElementHandler( "CSharpPgSqlSchemaObjMembers", getSchemaDefCSharpPgSqlSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CSharpPgSqlSchemaObjImpl", getSchemaDefCSharpPgSqlSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CSharpPgSqlSchemaObjUsing", getSchemaDefCSharpPgSqlSchemaObjUsingHandler() );
			schemaDefHandler.addElementHandler( "CSharpRamSchemaObjMembers", getSchemaDefCSharpRamSchemaObjMembersHandler() );
			schemaDefHandler.addElementHandler( "CSharpRamSchemaObjImpl", getSchemaDefCSharpRamSchemaObjImplHandler() );
			schemaDefHandler.addElementHandler( "CSharpRamSchemaObjUsing", getSchemaDefCSharpRamSchemaObjUsingHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgSchemaUsing", getSchemaDefCSharpXMsgSchemaUsingHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgSchemaFormatters", getSchemaDefCSharpXMsgSchemaFormattersHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgClientSchemaUsing", getSchemaDefCSharpXMsgClientSchemaUsingHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgClientSchemaBody", getSchemaDefCSharpXMsgClientSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgRqstSchemaBody", getSchemaDefCSharpXMsgRqstSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgRqstSchemaUsing", getSchemaDefCSharpXMsgRqstSchemaUsingHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgRqstSchemaWireParsers", getSchemaDefCSharpXMsgRqstSchemaWireParsersHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgRqstSchemaXsdSpec", getSchemaDefCSharpXMsgRqstSchemaXsdSpecHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgRqstSchemaXsdElementList", getSchemaDefCSharpXMsgRqstSchemaXsdElementListHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgRspnSchemaBody", getSchemaDefCSharpXMsgRspnSchemaBodyHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgRspnSchemaUsing", getSchemaDefCSharpXMsgRspnSchemaUsingHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgRspnSchemaWireParsers", getSchemaDefCSharpXMsgRspnSchemaWireParsersHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgRspnSchemaXsdElementList", getSchemaDefCSharpXMsgRspnSchemaXsdElementListHandler() );
			schemaDefHandler.addElementHandler( "CSharpXMsgRspnSchemaXsdSpec", getSchemaDefCSharpXMsgRspnSchemaXsdSpecHandler() );
		}
		return( schemaDefHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeSchemaObjImport getSchemaDefCafeSchemaObjImportHandler() {
		if( schemaDefCafeSchemaObjImportHandler == null ) {
			schemaDefCafeSchemaObjImportHandler = new CFBamSaxLoaderSchemaDefCafeSchemaObjImport( this );
		}
		return( schemaDefCafeSchemaObjImportHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeSchemaObjInterface getSchemaDefCafeSchemaObjInterfaceHandler() {
		if( schemaDefCafeSchemaObjInterfaceHandler == null ) {
			schemaDefCafeSchemaObjInterfaceHandler = new CFBamSaxLoaderSchemaDefCafeSchemaObjInterface( this );
		}
		return( schemaDefCafeSchemaObjInterfaceHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeSchemaObjMembers getSchemaDefCafeSchemaObjMembersHandler() {
		if( schemaDefCafeSchemaObjMembersHandler == null ) {
			schemaDefCafeSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCafeSchemaObjMembers( this );
		}
		return( schemaDefCafeSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeSchemaObjImplementation getSchemaDefCafeSchemaObjImplementationHandler() {
		if( schemaDefCafeSchemaObjImplementationHandler == null ) {
			schemaDefCafeSchemaObjImplementationHandler = new CFBamSaxLoaderSchemaDefCafeSchemaObjImplementation( this );
		}
		return( schemaDefCafeSchemaObjImplementationHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeDb2LUWSchemaObjMembers getSchemaDefCafeDb2LUWSchemaObjMembersHandler() {
		if( schemaDefCafeDb2LUWSchemaObjMembersHandler == null ) {
			schemaDefCafeDb2LUWSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCafeDb2LUWSchemaObjMembers( this );
		}
		return( schemaDefCafeDb2LUWSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeDb2LUWSchemaObjImpl getSchemaDefCafeDb2LUWSchemaObjImplHandler() {
		if( schemaDefCafeDb2LUWSchemaObjImplHandler == null ) {
			schemaDefCafeDb2LUWSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCafeDb2LUWSchemaObjImpl( this );
		}
		return( schemaDefCafeDb2LUWSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeDb2LUWSchemaObjImport getSchemaDefCafeDb2LUWSchemaObjImportHandler() {
		if( schemaDefCafeDb2LUWSchemaObjImportHandler == null ) {
			schemaDefCafeDb2LUWSchemaObjImportHandler = new CFBamSaxLoaderSchemaDefCafeDb2LUWSchemaObjImport( this );
		}
		return( schemaDefCafeDb2LUWSchemaObjImportHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeMSSqlSchemaObjMembers getSchemaDefCafeMSSqlSchemaObjMembersHandler() {
		if( schemaDefCafeMSSqlSchemaObjMembersHandler == null ) {
			schemaDefCafeMSSqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCafeMSSqlSchemaObjMembers( this );
		}
		return( schemaDefCafeMSSqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeMSSqlSchemaObjImpl getSchemaDefCafeMSSqlSchemaObjImplHandler() {
		if( schemaDefCafeMSSqlSchemaObjImplHandler == null ) {
			schemaDefCafeMSSqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCafeMSSqlSchemaObjImpl( this );
		}
		return( schemaDefCafeMSSqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeMSSqlSchemaObjImport getSchemaDefCafeMSSqlSchemaObjImportHandler() {
		if( schemaDefCafeMSSqlSchemaObjImportHandler == null ) {
			schemaDefCafeMSSqlSchemaObjImportHandler = new CFBamSaxLoaderSchemaDefCafeMSSqlSchemaObjImport( this );
		}
		return( schemaDefCafeMSSqlSchemaObjImportHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeMySqlSchemaObjMembers getSchemaDefCafeMySqlSchemaObjMembersHandler() {
		if( schemaDefCafeMySqlSchemaObjMembersHandler == null ) {
			schemaDefCafeMySqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCafeMySqlSchemaObjMembers( this );
		}
		return( schemaDefCafeMySqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeMySqlSchemaObjImpl getSchemaDefCafeMySqlSchemaObjImplHandler() {
		if( schemaDefCafeMySqlSchemaObjImplHandler == null ) {
			schemaDefCafeMySqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCafeMySqlSchemaObjImpl( this );
		}
		return( schemaDefCafeMySqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeMySqlSchemaObjImport getSchemaDefCafeMySqlSchemaObjImportHandler() {
		if( schemaDefCafeMySqlSchemaObjImportHandler == null ) {
			schemaDefCafeMySqlSchemaObjImportHandler = new CFBamSaxLoaderSchemaDefCafeMySqlSchemaObjImport( this );
		}
		return( schemaDefCafeMySqlSchemaObjImportHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeOracleSchemaObjMembers getSchemaDefCafeOracleSchemaObjMembersHandler() {
		if( schemaDefCafeOracleSchemaObjMembersHandler == null ) {
			schemaDefCafeOracleSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCafeOracleSchemaObjMembers( this );
		}
		return( schemaDefCafeOracleSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeOracleSchemaObjImpl getSchemaDefCafeOracleSchemaObjImplHandler() {
		if( schemaDefCafeOracleSchemaObjImplHandler == null ) {
			schemaDefCafeOracleSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCafeOracleSchemaObjImpl( this );
		}
		return( schemaDefCafeOracleSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeOracleSchemaObjImport getSchemaDefCafeOracleSchemaObjImportHandler() {
		if( schemaDefCafeOracleSchemaObjImportHandler == null ) {
			schemaDefCafeOracleSchemaObjImportHandler = new CFBamSaxLoaderSchemaDefCafeOracleSchemaObjImport( this );
		}
		return( schemaDefCafeOracleSchemaObjImportHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafePgSqlSchemaObjMembers getSchemaDefCafePgSqlSchemaObjMembersHandler() {
		if( schemaDefCafePgSqlSchemaObjMembersHandler == null ) {
			schemaDefCafePgSqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCafePgSqlSchemaObjMembers( this );
		}
		return( schemaDefCafePgSqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafePgSqlSchemaObjImpl getSchemaDefCafePgSqlSchemaObjImplHandler() {
		if( schemaDefCafePgSqlSchemaObjImplHandler == null ) {
			schemaDefCafePgSqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCafePgSqlSchemaObjImpl( this );
		}
		return( schemaDefCafePgSqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafePgSqlSchemaObjImport getSchemaDefCafePgSqlSchemaObjImportHandler() {
		if( schemaDefCafePgSqlSchemaObjImportHandler == null ) {
			schemaDefCafePgSqlSchemaObjImportHandler = new CFBamSaxLoaderSchemaDefCafePgSqlSchemaObjImport( this );
		}
		return( schemaDefCafePgSqlSchemaObjImportHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeRamSchemaObjMembers getSchemaDefCafeRamSchemaObjMembersHandler() {
		if( schemaDefCafeRamSchemaObjMembersHandler == null ) {
			schemaDefCafeRamSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCafeRamSchemaObjMembers( this );
		}
		return( schemaDefCafeRamSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeRamSchemaObjImpl getSchemaDefCafeRamSchemaObjImplHandler() {
		if( schemaDefCafeRamSchemaObjImplHandler == null ) {
			schemaDefCafeRamSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCafeRamSchemaObjImpl( this );
		}
		return( schemaDefCafeRamSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeRamSchemaObjImport getSchemaDefCafeRamSchemaObjImportHandler() {
		if( schemaDefCafeRamSchemaObjImportHandler == null ) {
			schemaDefCafeRamSchemaObjImportHandler = new CFBamSaxLoaderSchemaDefCafeRamSchemaObjImport( this );
		}
		return( schemaDefCafeRamSchemaObjImportHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgSchemaImport getSchemaDefCafeXMsgSchemaImportHandler() {
		if( schemaDefCafeXMsgSchemaImportHandler == null ) {
			schemaDefCafeXMsgSchemaImportHandler = new CFBamSaxLoaderSchemaDefCafeXMsgSchemaImport( this );
		}
		return( schemaDefCafeXMsgSchemaImportHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgSchemaFormatters getSchemaDefCafeXMsgSchemaFormattersHandler() {
		if( schemaDefCafeXMsgSchemaFormattersHandler == null ) {
			schemaDefCafeXMsgSchemaFormattersHandler = new CFBamSaxLoaderSchemaDefCafeXMsgSchemaFormatters( this );
		}
		return( schemaDefCafeXMsgSchemaFormattersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgClientSchemaImport getSchemaDefCafeXMsgClientSchemaImportHandler() {
		if( schemaDefCafeXMsgClientSchemaImportHandler == null ) {
			schemaDefCafeXMsgClientSchemaImportHandler = new CFBamSaxLoaderSchemaDefCafeXMsgClientSchemaImport( this );
		}
		return( schemaDefCafeXMsgClientSchemaImportHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgClientSchemaBody getSchemaDefCafeXMsgClientSchemaBodyHandler() {
		if( schemaDefCafeXMsgClientSchemaBodyHandler == null ) {
			schemaDefCafeXMsgClientSchemaBodyHandler = new CFBamSaxLoaderSchemaDefCafeXMsgClientSchemaBody( this );
		}
		return( schemaDefCafeXMsgClientSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaBody getSchemaDefCafeXMsgRqstSchemaBodyHandler() {
		if( schemaDefCafeXMsgRqstSchemaBodyHandler == null ) {
			schemaDefCafeXMsgRqstSchemaBodyHandler = new CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaBody( this );
		}
		return( schemaDefCafeXMsgRqstSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaImport getSchemaDefCafeXMsgRqstSchemaImportHandler() {
		if( schemaDefCafeXMsgRqstSchemaImportHandler == null ) {
			schemaDefCafeXMsgRqstSchemaImportHandler = new CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaImport( this );
		}
		return( schemaDefCafeXMsgRqstSchemaImportHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaWireParsers getSchemaDefCafeXMsgRqstSchemaWireParsersHandler() {
		if( schemaDefCafeXMsgRqstSchemaWireParsersHandler == null ) {
			schemaDefCafeXMsgRqstSchemaWireParsersHandler = new CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaWireParsers( this );
		}
		return( schemaDefCafeXMsgRqstSchemaWireParsersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaXsdSpec getSchemaDefCafeXMsgRqstSchemaXsdSpecHandler() {
		if( schemaDefCafeXMsgRqstSchemaXsdSpecHandler == null ) {
			schemaDefCafeXMsgRqstSchemaXsdSpecHandler = new CFBamSaxLoaderSchemaDefCafeXMsgRqstSchemaXsdSpec( this );
		}
		return( schemaDefCafeXMsgRqstSchemaXsdSpecHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeSchemaXsdElementList getSchemaDefCafeSchemaXsdElementListHandler() {
		if( schemaDefCafeSchemaXsdElementListHandler == null ) {
			schemaDefCafeSchemaXsdElementListHandler = new CFBamSaxLoaderSchemaDefCafeSchemaXsdElementList( this );
		}
		return( schemaDefCafeSchemaXsdElementListHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaBody getSchemaDefCafeXMsgRspnSchemaBodyHandler() {
		if( schemaDefCafeXMsgRspnSchemaBodyHandler == null ) {
			schemaDefCafeXMsgRspnSchemaBodyHandler = new CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaBody( this );
		}
		return( schemaDefCafeXMsgRspnSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaImport getSchemaDefCafeXMsgRspnSchemaImportHandler() {
		if( schemaDefCafeXMsgRspnSchemaImportHandler == null ) {
			schemaDefCafeXMsgRspnSchemaImportHandler = new CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaImport( this );
		}
		return( schemaDefCafeXMsgRspnSchemaImportHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaWireParsers getSchemaDefCafeXMsgRspnSchemaWireParsersHandler() {
		if( schemaDefCafeXMsgRspnSchemaWireParsersHandler == null ) {
			schemaDefCafeXMsgRspnSchemaWireParsersHandler = new CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaWireParsers( this );
		}
		return( schemaDefCafeXMsgRspnSchemaWireParsersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaXsdElementList getSchemaDefCafeXMsgRspnSchemaXsdElementListHandler() {
		if( schemaDefCafeXMsgRspnSchemaXsdElementListHandler == null ) {
			schemaDefCafeXMsgRspnSchemaXsdElementListHandler = new CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaXsdElementList( this );
		}
		return( schemaDefCafeXMsgRspnSchemaXsdElementListHandler );
	}
	protected CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaXsdSpec getSchemaDefCafeXMsgRspnSchemaXsdSpecHandler() {
		if( schemaDefCafeXMsgRspnSchemaXsdSpecHandler == null ) {
			schemaDefCafeXMsgRspnSchemaXsdSpecHandler = new CFBamSaxLoaderSchemaDefCafeXMsgRspnSchemaXsdSpec( this );
		}
		return( schemaDefCafeXMsgRspnSchemaXsdSpecHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusSchemaObjInclude getSchemaDefCPlusSchemaObjIncludeHandler() {
		if( schemaDefCPlusSchemaObjIncludeHandler == null ) {
			schemaDefCPlusSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefCPlusSchemaObjInclude( this );
		}
		return( schemaDefCPlusSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusSchemaObjInterface getSchemaDefCPlusSchemaObjInterfaceHandler() {
		if( schemaDefCPlusSchemaObjInterfaceHandler == null ) {
			schemaDefCPlusSchemaObjInterfaceHandler = new CFBamSaxLoaderSchemaDefCPlusSchemaObjInterface( this );
		}
		return( schemaDefCPlusSchemaObjInterfaceHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusSchemaObjMembers getSchemaDefCPlusSchemaObjMembersHandler() {
		if( schemaDefCPlusSchemaObjMembersHandler == null ) {
			schemaDefCPlusSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCPlusSchemaObjMembers( this );
		}
		return( schemaDefCPlusSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusSchemaObjImplementation getSchemaDefCPlusSchemaObjImplementationHandler() {
		if( schemaDefCPlusSchemaObjImplementationHandler == null ) {
			schemaDefCPlusSchemaObjImplementationHandler = new CFBamSaxLoaderSchemaDefCPlusSchemaObjImplementation( this );
		}
		return( schemaDefCPlusSchemaObjImplementationHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusDb2LUWSchemaObjMembers getSchemaDefCPlusDb2LUWSchemaObjMembersHandler() {
		if( schemaDefCPlusDb2LUWSchemaObjMembersHandler == null ) {
			schemaDefCPlusDb2LUWSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCPlusDb2LUWSchemaObjMembers( this );
		}
		return( schemaDefCPlusDb2LUWSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusDb2LUWSchemaObjImpl getSchemaDefCPlusDb2LUWSchemaObjImplHandler() {
		if( schemaDefCPlusDb2LUWSchemaObjImplHandler == null ) {
			schemaDefCPlusDb2LUWSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCPlusDb2LUWSchemaObjImpl( this );
		}
		return( schemaDefCPlusDb2LUWSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusDb2LUWSchemaObjInclude getSchemaDefCPlusDb2LUWSchemaObjIncludeHandler() {
		if( schemaDefCPlusDb2LUWSchemaObjIncludeHandler == null ) {
			schemaDefCPlusDb2LUWSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefCPlusDb2LUWSchemaObjInclude( this );
		}
		return( schemaDefCPlusDb2LUWSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusMSSqlSchemaObjMembers getSchemaDefCPlusMSSqlSchemaObjMembersHandler() {
		if( schemaDefCPlusMSSqlSchemaObjMembersHandler == null ) {
			schemaDefCPlusMSSqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCPlusMSSqlSchemaObjMembers( this );
		}
		return( schemaDefCPlusMSSqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusMSSqlSchemaObjImpl getSchemaDefCPlusMSSqlSchemaObjImplHandler() {
		if( schemaDefCPlusMSSqlSchemaObjImplHandler == null ) {
			schemaDefCPlusMSSqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCPlusMSSqlSchemaObjImpl( this );
		}
		return( schemaDefCPlusMSSqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusMSSqlSchemaObjInclude getSchemaDefCPlusMSSqlSchemaObjIncludeHandler() {
		if( schemaDefCPlusMSSqlSchemaObjIncludeHandler == null ) {
			schemaDefCPlusMSSqlSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefCPlusMSSqlSchemaObjInclude( this );
		}
		return( schemaDefCPlusMSSqlSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusMySqlSchemaObjMembers getSchemaDefCPlusMySqlSchemaObjMembersHandler() {
		if( schemaDefCPlusMySqlSchemaObjMembersHandler == null ) {
			schemaDefCPlusMySqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCPlusMySqlSchemaObjMembers( this );
		}
		return( schemaDefCPlusMySqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusMySqlSchemaObjImpl getSchemaDefCPlusMySqlSchemaObjImplHandler() {
		if( schemaDefCPlusMySqlSchemaObjImplHandler == null ) {
			schemaDefCPlusMySqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCPlusMySqlSchemaObjImpl( this );
		}
		return( schemaDefCPlusMySqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusMySqlSchemaObjInclude getSchemaDefCPlusMySqlSchemaObjIncludeHandler() {
		if( schemaDefCPlusMySqlSchemaObjIncludeHandler == null ) {
			schemaDefCPlusMySqlSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefCPlusMySqlSchemaObjInclude( this );
		}
		return( schemaDefCPlusMySqlSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusOracleSchemaObjMembers getSchemaDefCPlusOracleSchemaObjMembersHandler() {
		if( schemaDefCPlusOracleSchemaObjMembersHandler == null ) {
			schemaDefCPlusOracleSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCPlusOracleSchemaObjMembers( this );
		}
		return( schemaDefCPlusOracleSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusOracleSchemaObjImpl getSchemaDefCPlusOracleSchemaObjImplHandler() {
		if( schemaDefCPlusOracleSchemaObjImplHandler == null ) {
			schemaDefCPlusOracleSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCPlusOracleSchemaObjImpl( this );
		}
		return( schemaDefCPlusOracleSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusOracleSchemaObjInclude getSchemaDefCPlusOracleSchemaObjIncludeHandler() {
		if( schemaDefCPlusOracleSchemaObjIncludeHandler == null ) {
			schemaDefCPlusOracleSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefCPlusOracleSchemaObjInclude( this );
		}
		return( schemaDefCPlusOracleSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusPgSqlSchemaObjMembers getSchemaDefCPlusPgSqlSchemaObjMembersHandler() {
		if( schemaDefCPlusPgSqlSchemaObjMembersHandler == null ) {
			schemaDefCPlusPgSqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCPlusPgSqlSchemaObjMembers( this );
		}
		return( schemaDefCPlusPgSqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusPgSqlSchemaObjImpl getSchemaDefCPlusPgSqlSchemaObjImplHandler() {
		if( schemaDefCPlusPgSqlSchemaObjImplHandler == null ) {
			schemaDefCPlusPgSqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCPlusPgSqlSchemaObjImpl( this );
		}
		return( schemaDefCPlusPgSqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusPgSqlSchemaObjInclude getSchemaDefCPlusPgSqlSchemaObjIncludeHandler() {
		if( schemaDefCPlusPgSqlSchemaObjIncludeHandler == null ) {
			schemaDefCPlusPgSqlSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefCPlusPgSqlSchemaObjInclude( this );
		}
		return( schemaDefCPlusPgSqlSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusRamSchemaObjMembers getSchemaDefCPlusRamSchemaObjMembersHandler() {
		if( schemaDefCPlusRamSchemaObjMembersHandler == null ) {
			schemaDefCPlusRamSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCPlusRamSchemaObjMembers( this );
		}
		return( schemaDefCPlusRamSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusRamSchemaObjImpl getSchemaDefCPlusRamSchemaObjImplHandler() {
		if( schemaDefCPlusRamSchemaObjImplHandler == null ) {
			schemaDefCPlusRamSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCPlusRamSchemaObjImpl( this );
		}
		return( schemaDefCPlusRamSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusRamSchemaObjInclude getSchemaDefCPlusRamSchemaObjIncludeHandler() {
		if( schemaDefCPlusRamSchemaObjIncludeHandler == null ) {
			schemaDefCPlusRamSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefCPlusRamSchemaObjInclude( this );
		}
		return( schemaDefCPlusRamSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgSchemaInclude getSchemaDefCPlusXMsgSchemaIncludeHandler() {
		if( schemaDefCPlusXMsgSchemaIncludeHandler == null ) {
			schemaDefCPlusXMsgSchemaIncludeHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgSchemaInclude( this );
		}
		return( schemaDefCPlusXMsgSchemaIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgSchemaFormatters getSchemaDefCPlusXMsgSchemaFormattersHandler() {
		if( schemaDefCPlusXMsgSchemaFormattersHandler == null ) {
			schemaDefCPlusXMsgSchemaFormattersHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgSchemaFormatters( this );
		}
		return( schemaDefCPlusXMsgSchemaFormattersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgClientSchemaInclude getSchemaDefCPlusXMsgClientSchemaIncludeHandler() {
		if( schemaDefCPlusXMsgClientSchemaIncludeHandler == null ) {
			schemaDefCPlusXMsgClientSchemaIncludeHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgClientSchemaInclude( this );
		}
		return( schemaDefCPlusXMsgClientSchemaIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgClientSchemaBody getSchemaDefCPlusXMsgClientSchemaBodyHandler() {
		if( schemaDefCPlusXMsgClientSchemaBodyHandler == null ) {
			schemaDefCPlusXMsgClientSchemaBodyHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgClientSchemaBody( this );
		}
		return( schemaDefCPlusXMsgClientSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaBody getSchemaDefCPlusXMsgRqstSchemaBodyHandler() {
		if( schemaDefCPlusXMsgRqstSchemaBodyHandler == null ) {
			schemaDefCPlusXMsgRqstSchemaBodyHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaBody( this );
		}
		return( schemaDefCPlusXMsgRqstSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaInclude getSchemaDefCPlusXMsgRqstSchemaIncludeHandler() {
		if( schemaDefCPlusXMsgRqstSchemaIncludeHandler == null ) {
			schemaDefCPlusXMsgRqstSchemaIncludeHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaInclude( this );
		}
		return( schemaDefCPlusXMsgRqstSchemaIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaWireParsers getSchemaDefCPlusXMsgRqstSchemaWireParsersHandler() {
		if( schemaDefCPlusXMsgRqstSchemaWireParsersHandler == null ) {
			schemaDefCPlusXMsgRqstSchemaWireParsersHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaWireParsers( this );
		}
		return( schemaDefCPlusXMsgRqstSchemaWireParsersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaXsdSpec getSchemaDefCPlusXMsgRqstSchemaXsdSpecHandler() {
		if( schemaDefCPlusXMsgRqstSchemaXsdSpecHandler == null ) {
			schemaDefCPlusXMsgRqstSchemaXsdSpecHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaXsdSpec( this );
		}
		return( schemaDefCPlusXMsgRqstSchemaXsdSpecHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaXsdElementList getSchemaDefCPlusXMsgRqstSchemaXsdElementListHandler() {
		if( schemaDefCPlusXMsgRqstSchemaXsdElementListHandler == null ) {
			schemaDefCPlusXMsgRqstSchemaXsdElementListHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgRqstSchemaXsdElementList( this );
		}
		return( schemaDefCPlusXMsgRqstSchemaXsdElementListHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXmsgRspnSchemaBody getSchemaDefCPlusXmsgRspnSchemaBodyHandler() {
		if( schemaDefCPlusXmsgRspnSchemaBodyHandler == null ) {
			schemaDefCPlusXmsgRspnSchemaBodyHandler = new CFBamSaxLoaderSchemaDefCPlusXmsgRspnSchemaBody( this );
		}
		return( schemaDefCPlusXmsgRspnSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaInclude getSchemaDefCPlusXMsgRspnSchemaIncludeHandler() {
		if( schemaDefCPlusXMsgRspnSchemaIncludeHandler == null ) {
			schemaDefCPlusXMsgRspnSchemaIncludeHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaInclude( this );
		}
		return( schemaDefCPlusXMsgRspnSchemaIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaWireParsers getSchemaDefCPlusXMsgRspnSchemaWireParsersHandler() {
		if( schemaDefCPlusXMsgRspnSchemaWireParsersHandler == null ) {
			schemaDefCPlusXMsgRspnSchemaWireParsersHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaWireParsers( this );
		}
		return( schemaDefCPlusXMsgRspnSchemaWireParsersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaXsdElementList getSchemaDefCPlusXMsgRspnSchemaXsdElementListHandler() {
		if( schemaDefCPlusXMsgRspnSchemaXsdElementListHandler == null ) {
			schemaDefCPlusXMsgRspnSchemaXsdElementListHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaXsdElementList( this );
		}
		return( schemaDefCPlusXMsgRspnSchemaXsdElementListHandler );
	}
	protected CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaXsdSpec getSchemaDefCPlusXMsgRspnSchemaXsdSpecHandler() {
		if( schemaDefCPlusXMsgRspnSchemaXsdSpecHandler == null ) {
			schemaDefCPlusXMsgRspnSchemaXsdSpecHandler = new CFBamSaxLoaderSchemaDefCPlusXMsgRspnSchemaXsdSpec( this );
		}
		return( schemaDefCPlusXMsgRspnSchemaXsdSpecHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusSchemaObjInclude getSchemaDefHPlusSchemaObjIncludeHandler() {
		if( schemaDefHPlusSchemaObjIncludeHandler == null ) {
			schemaDefHPlusSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefHPlusSchemaObjInclude( this );
		}
		return( schemaDefHPlusSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusSchemaObjInterface getSchemaDefHPlusSchemaObjInterfaceHandler() {
		if( schemaDefHPlusSchemaObjInterfaceHandler == null ) {
			schemaDefHPlusSchemaObjInterfaceHandler = new CFBamSaxLoaderSchemaDefHPlusSchemaObjInterface( this );
		}
		return( schemaDefHPlusSchemaObjInterfaceHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusSchemaObjMembers getSchemaDefHPlusSchemaObjMembersHandler() {
		if( schemaDefHPlusSchemaObjMembersHandler == null ) {
			schemaDefHPlusSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefHPlusSchemaObjMembers( this );
		}
		return( schemaDefHPlusSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusSchemaObjImplementation getSchemaDefHPlusSchemaObjImplementationHandler() {
		if( schemaDefHPlusSchemaObjImplementationHandler == null ) {
			schemaDefHPlusSchemaObjImplementationHandler = new CFBamSaxLoaderSchemaDefHPlusSchemaObjImplementation( this );
		}
		return( schemaDefHPlusSchemaObjImplementationHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusDb2LUWSchemaObjMembers getSchemaDefHPlusDb2LUWSchemaObjMembersHandler() {
		if( schemaDefHPlusDb2LUWSchemaObjMembersHandler == null ) {
			schemaDefHPlusDb2LUWSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefHPlusDb2LUWSchemaObjMembers( this );
		}
		return( schemaDefHPlusDb2LUWSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusDb2LUWSchemaObjImpl getSchemaDefHPlusDb2LUWSchemaObjImplHandler() {
		if( schemaDefHPlusDb2LUWSchemaObjImplHandler == null ) {
			schemaDefHPlusDb2LUWSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefHPlusDb2LUWSchemaObjImpl( this );
		}
		return( schemaDefHPlusDb2LUWSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusDb2LUWSchemaObjInclude getSchemaDefHPlusDb2LUWSchemaObjIncludeHandler() {
		if( schemaDefHPlusDb2LUWSchemaObjIncludeHandler == null ) {
			schemaDefHPlusDb2LUWSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefHPlusDb2LUWSchemaObjInclude( this );
		}
		return( schemaDefHPlusDb2LUWSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusMSSqlSchemaObjMembers getSchemaDefHPlusMSSqlSchemaObjMembersHandler() {
		if( schemaDefHPlusMSSqlSchemaObjMembersHandler == null ) {
			schemaDefHPlusMSSqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefHPlusMSSqlSchemaObjMembers( this );
		}
		return( schemaDefHPlusMSSqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusMSSqlSchemaObjImpl getSchemaDefHPlusMSSqlSchemaObjImplHandler() {
		if( schemaDefHPlusMSSqlSchemaObjImplHandler == null ) {
			schemaDefHPlusMSSqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefHPlusMSSqlSchemaObjImpl( this );
		}
		return( schemaDefHPlusMSSqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusMSSqlSchemaObjInclude getSchemaDefHPlusMSSqlSchemaObjIncludeHandler() {
		if( schemaDefHPlusMSSqlSchemaObjIncludeHandler == null ) {
			schemaDefHPlusMSSqlSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefHPlusMSSqlSchemaObjInclude( this );
		}
		return( schemaDefHPlusMSSqlSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusMySqlSchemaObjMembers getSchemaDefHPlusMySqlSchemaObjMembersHandler() {
		if( schemaDefHPlusMySqlSchemaObjMembersHandler == null ) {
			schemaDefHPlusMySqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefHPlusMySqlSchemaObjMembers( this );
		}
		return( schemaDefHPlusMySqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusMySqlSchemaObjImpl getSchemaDefHPlusMySqlSchemaObjImplHandler() {
		if( schemaDefHPlusMySqlSchemaObjImplHandler == null ) {
			schemaDefHPlusMySqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefHPlusMySqlSchemaObjImpl( this );
		}
		return( schemaDefHPlusMySqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusMySqlSchemaObjInclude getSchemaDefHPlusMySqlSchemaObjIncludeHandler() {
		if( schemaDefHPlusMySqlSchemaObjIncludeHandler == null ) {
			schemaDefHPlusMySqlSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefHPlusMySqlSchemaObjInclude( this );
		}
		return( schemaDefHPlusMySqlSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusOracleSchemaObjMembers getSchemaDefHPlusOracleSchemaObjMembersHandler() {
		if( schemaDefHPlusOracleSchemaObjMembersHandler == null ) {
			schemaDefHPlusOracleSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefHPlusOracleSchemaObjMembers( this );
		}
		return( schemaDefHPlusOracleSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusOracleSchemaObjImpl getSchemaDefHPlusOracleSchemaObjImplHandler() {
		if( schemaDefHPlusOracleSchemaObjImplHandler == null ) {
			schemaDefHPlusOracleSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefHPlusOracleSchemaObjImpl( this );
		}
		return( schemaDefHPlusOracleSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusOracleSchemaObjInclude getSchemaDefHPlusOracleSchemaObjIncludeHandler() {
		if( schemaDefHPlusOracleSchemaObjIncludeHandler == null ) {
			schemaDefHPlusOracleSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefHPlusOracleSchemaObjInclude( this );
		}
		return( schemaDefHPlusOracleSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusPgSqlSchemaObjMembers getSchemaDefHPlusPgSqlSchemaObjMembersHandler() {
		if( schemaDefHPlusPgSqlSchemaObjMembersHandler == null ) {
			schemaDefHPlusPgSqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefHPlusPgSqlSchemaObjMembers( this );
		}
		return( schemaDefHPlusPgSqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusPgSqlSchemaObjImpl getSchemaDefHPlusPgSqlSchemaObjImplHandler() {
		if( schemaDefHPlusPgSqlSchemaObjImplHandler == null ) {
			schemaDefHPlusPgSqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefHPlusPgSqlSchemaObjImpl( this );
		}
		return( schemaDefHPlusPgSqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusPgSqlSchemaObjInclude getSchemaDefHPlusPgSqlSchemaObjIncludeHandler() {
		if( schemaDefHPlusPgSqlSchemaObjIncludeHandler == null ) {
			schemaDefHPlusPgSqlSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefHPlusPgSqlSchemaObjInclude( this );
		}
		return( schemaDefHPlusPgSqlSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusRamSchemaObjMembers getSchemaDefHPlusRamSchemaObjMembersHandler() {
		if( schemaDefHPlusRamSchemaObjMembersHandler == null ) {
			schemaDefHPlusRamSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefHPlusRamSchemaObjMembers( this );
		}
		return( schemaDefHPlusRamSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusRamSchemaObjImpl getSchemaDefHPlusRamSchemaObjImplHandler() {
		if( schemaDefHPlusRamSchemaObjImplHandler == null ) {
			schemaDefHPlusRamSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefHPlusRamSchemaObjImpl( this );
		}
		return( schemaDefHPlusRamSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusRamSchemaObjInclude getSchemaDefHPlusRamSchemaObjIncludeHandler() {
		if( schemaDefHPlusRamSchemaObjIncludeHandler == null ) {
			schemaDefHPlusRamSchemaObjIncludeHandler = new CFBamSaxLoaderSchemaDefHPlusRamSchemaObjInclude( this );
		}
		return( schemaDefHPlusRamSchemaObjIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgSchemaInclude getSchemaDefHPlusXMsgSchemaIncludeHandler() {
		if( schemaDefHPlusXMsgSchemaIncludeHandler == null ) {
			schemaDefHPlusXMsgSchemaIncludeHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgSchemaInclude( this );
		}
		return( schemaDefHPlusXMsgSchemaIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgSchemaFormatters getSchemaDefHPlusXMsgSchemaFormattersHandler() {
		if( schemaDefHPlusXMsgSchemaFormattersHandler == null ) {
			schemaDefHPlusXMsgSchemaFormattersHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgSchemaFormatters( this );
		}
		return( schemaDefHPlusXMsgSchemaFormattersHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgClientSchemaInclude getSchemaDefHPlusXMsgClientSchemaIncludeHandler() {
		if( schemaDefHPlusXMsgClientSchemaIncludeHandler == null ) {
			schemaDefHPlusXMsgClientSchemaIncludeHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgClientSchemaInclude( this );
		}
		return( schemaDefHPlusXMsgClientSchemaIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgClientSchemaBody getSchemaDefHPlusXMsgClientSchemaBodyHandler() {
		if( schemaDefHPlusXMsgClientSchemaBodyHandler == null ) {
			schemaDefHPlusXMsgClientSchemaBodyHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgClientSchemaBody( this );
		}
		return( schemaDefHPlusXMsgClientSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaBody getSchemaDefHPlusXMsgRqstSchemaBodyHandler() {
		if( schemaDefHPlusXMsgRqstSchemaBodyHandler == null ) {
			schemaDefHPlusXMsgRqstSchemaBodyHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaBody( this );
		}
		return( schemaDefHPlusXMsgRqstSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaInclude getSchemaDefHPlusXMsgRqstSchemaIncludeHandler() {
		if( schemaDefHPlusXMsgRqstSchemaIncludeHandler == null ) {
			schemaDefHPlusXMsgRqstSchemaIncludeHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaInclude( this );
		}
		return( schemaDefHPlusXMsgRqstSchemaIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaWireParsers getSchemaDefHPlusXMsgRqstSchemaWireParsersHandler() {
		if( schemaDefHPlusXMsgRqstSchemaWireParsersHandler == null ) {
			schemaDefHPlusXMsgRqstSchemaWireParsersHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaWireParsers( this );
		}
		return( schemaDefHPlusXMsgRqstSchemaWireParsersHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaXsdSpec getSchemaDefHPlusXMsgRqstSchemaXsdSpecHandler() {
		if( schemaDefHPlusXMsgRqstSchemaXsdSpecHandler == null ) {
			schemaDefHPlusXMsgRqstSchemaXsdSpecHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaXsdSpec( this );
		}
		return( schemaDefHPlusXMsgRqstSchemaXsdSpecHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaXsdElementList getSchemaDefHPlusXMsgRqstSchemaXsdElementListHandler() {
		if( schemaDefHPlusXMsgRqstSchemaXsdElementListHandler == null ) {
			schemaDefHPlusXMsgRqstSchemaXsdElementListHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgRqstSchemaXsdElementList( this );
		}
		return( schemaDefHPlusXMsgRqstSchemaXsdElementListHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaBody getSchemaDefHPlusXMsgRspnSchemaBodyHandler() {
		if( schemaDefHPlusXMsgRspnSchemaBodyHandler == null ) {
			schemaDefHPlusXMsgRspnSchemaBodyHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaBody( this );
		}
		return( schemaDefHPlusXMsgRspnSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaInclude getSchemaDefHPlusXMsgRspnSchemaIncludeHandler() {
		if( schemaDefHPlusXMsgRspnSchemaIncludeHandler == null ) {
			schemaDefHPlusXMsgRspnSchemaIncludeHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaInclude( this );
		}
		return( schemaDefHPlusXMsgRspnSchemaIncludeHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaWireParsers getSchemaDefHPlusXMsgRspnSchemaWireParsersHandler() {
		if( schemaDefHPlusXMsgRspnSchemaWireParsersHandler == null ) {
			schemaDefHPlusXMsgRspnSchemaWireParsersHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaWireParsers( this );
		}
		return( schemaDefHPlusXMsgRspnSchemaWireParsersHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaXsdElementList getSchemaDefHPlusXMsgRspnSchemaXsdElementListHandler() {
		if( schemaDefHPlusXMsgRspnSchemaXsdElementListHandler == null ) {
			schemaDefHPlusXMsgRspnSchemaXsdElementListHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaXsdElementList( this );
		}
		return( schemaDefHPlusXMsgRspnSchemaXsdElementListHandler );
	}
	protected CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaXsdSpec getSchemaDefHPlusXMsgRspnSchemaXsdSpecHandler() {
		if( schemaDefHPlusXMsgRspnSchemaXsdSpecHandler == null ) {
			schemaDefHPlusXMsgRspnSchemaXsdSpecHandler = new CFBamSaxLoaderSchemaDefHPlusXMsgRspnSchemaXsdSpec( this );
		}
		return( schemaDefHPlusXMsgRspnSchemaXsdSpecHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpSchemaObjUsing getSchemaDefCSharpSchemaObjUsingHandler() {
		if( schemaDefCSharpSchemaObjUsingHandler == null ) {
			schemaDefCSharpSchemaObjUsingHandler = new CFBamSaxLoaderSchemaDefCSharpSchemaObjUsing( this );
		}
		return( schemaDefCSharpSchemaObjUsingHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpSchemaObjInterface getSchemaDefCSharpSchemaObjInterfaceHandler() {
		if( schemaDefCSharpSchemaObjInterfaceHandler == null ) {
			schemaDefCSharpSchemaObjInterfaceHandler = new CFBamSaxLoaderSchemaDefCSharpSchemaObjInterface( this );
		}
		return( schemaDefCSharpSchemaObjInterfaceHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpSchemaObjMembers getSchemaDefCSharpSchemaObjMembersHandler() {
		if( schemaDefCSharpSchemaObjMembersHandler == null ) {
			schemaDefCSharpSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCSharpSchemaObjMembers( this );
		}
		return( schemaDefCSharpSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpSchemaObjImplementation getSchemaDefCSharpSchemaObjImplementationHandler() {
		if( schemaDefCSharpSchemaObjImplementationHandler == null ) {
			schemaDefCSharpSchemaObjImplementationHandler = new CFBamSaxLoaderSchemaDefCSharpSchemaObjImplementation( this );
		}
		return( schemaDefCSharpSchemaObjImplementationHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpDb2LUWSchemaObjMembers getSchemaDefCSharpDb2LUWSchemaObjMembersHandler() {
		if( schemaDefCSharpDb2LUWSchemaObjMembersHandler == null ) {
			schemaDefCSharpDb2LUWSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCSharpDb2LUWSchemaObjMembers( this );
		}
		return( schemaDefCSharpDb2LUWSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpDb2LUWSchemaObjImpl getSchemaDefCSharpDb2LUWSchemaObjImplHandler() {
		if( schemaDefCSharpDb2LUWSchemaObjImplHandler == null ) {
			schemaDefCSharpDb2LUWSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCSharpDb2LUWSchemaObjImpl( this );
		}
		return( schemaDefCSharpDb2LUWSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpDb2LUWSchemaObjUsing getSchemaDefCSharpDb2LUWSchemaObjUsingHandler() {
		if( schemaDefCSharpDb2LUWSchemaObjUsingHandler == null ) {
			schemaDefCSharpDb2LUWSchemaObjUsingHandler = new CFBamSaxLoaderSchemaDefCSharpDb2LUWSchemaObjUsing( this );
		}
		return( schemaDefCSharpDb2LUWSchemaObjUsingHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpMSSqlSchemaObjMembers getSchemaDefCSharpMSSqlSchemaObjMembersHandler() {
		if( schemaDefCSharpMSSqlSchemaObjMembersHandler == null ) {
			schemaDefCSharpMSSqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCSharpMSSqlSchemaObjMembers( this );
		}
		return( schemaDefCSharpMSSqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpMSSqlSchemaObjImpl getSchemaDefCSharpMSSqlSchemaObjImplHandler() {
		if( schemaDefCSharpMSSqlSchemaObjImplHandler == null ) {
			schemaDefCSharpMSSqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCSharpMSSqlSchemaObjImpl( this );
		}
		return( schemaDefCSharpMSSqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpMSSqlSchemaObjUsing getSchemaDefCSharpMSSqlSchemaObjUsingHandler() {
		if( schemaDefCSharpMSSqlSchemaObjUsingHandler == null ) {
			schemaDefCSharpMSSqlSchemaObjUsingHandler = new CFBamSaxLoaderSchemaDefCSharpMSSqlSchemaObjUsing( this );
		}
		return( schemaDefCSharpMSSqlSchemaObjUsingHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpMySqlSchemaObjMembers getSchemaDefCSharpMySqlSchemaObjMembersHandler() {
		if( schemaDefCSharpMySqlSchemaObjMembersHandler == null ) {
			schemaDefCSharpMySqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCSharpMySqlSchemaObjMembers( this );
		}
		return( schemaDefCSharpMySqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpMySqlSchemaObjImpl getSchemaDefCSharpMySqlSchemaObjImplHandler() {
		if( schemaDefCSharpMySqlSchemaObjImplHandler == null ) {
			schemaDefCSharpMySqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCSharpMySqlSchemaObjImpl( this );
		}
		return( schemaDefCSharpMySqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpMySqlSchemaObjUsing getSchemaDefCSharpMySqlSchemaObjUsingHandler() {
		if( schemaDefCSharpMySqlSchemaObjUsingHandler == null ) {
			schemaDefCSharpMySqlSchemaObjUsingHandler = new CFBamSaxLoaderSchemaDefCSharpMySqlSchemaObjUsing( this );
		}
		return( schemaDefCSharpMySqlSchemaObjUsingHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpOracleSchemaObjMembers getSchemaDefCSharpOracleSchemaObjMembersHandler() {
		if( schemaDefCSharpOracleSchemaObjMembersHandler == null ) {
			schemaDefCSharpOracleSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCSharpOracleSchemaObjMembers( this );
		}
		return( schemaDefCSharpOracleSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpOracleSchemaObjImpl getSchemaDefCSharpOracleSchemaObjImplHandler() {
		if( schemaDefCSharpOracleSchemaObjImplHandler == null ) {
			schemaDefCSharpOracleSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCSharpOracleSchemaObjImpl( this );
		}
		return( schemaDefCSharpOracleSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpOracleSchemaObjUsing getSchemaDefCSharpOracleSchemaObjUsingHandler() {
		if( schemaDefCSharpOracleSchemaObjUsingHandler == null ) {
			schemaDefCSharpOracleSchemaObjUsingHandler = new CFBamSaxLoaderSchemaDefCSharpOracleSchemaObjUsing( this );
		}
		return( schemaDefCSharpOracleSchemaObjUsingHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpPgSqlSchemaObjMembers getSchemaDefCSharpPgSqlSchemaObjMembersHandler() {
		if( schemaDefCSharpPgSqlSchemaObjMembersHandler == null ) {
			schemaDefCSharpPgSqlSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCSharpPgSqlSchemaObjMembers( this );
		}
		return( schemaDefCSharpPgSqlSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpPgSqlSchemaObjImpl getSchemaDefCSharpPgSqlSchemaObjImplHandler() {
		if( schemaDefCSharpPgSqlSchemaObjImplHandler == null ) {
			schemaDefCSharpPgSqlSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCSharpPgSqlSchemaObjImpl( this );
		}
		return( schemaDefCSharpPgSqlSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpPgSqlSchemaObjUsing getSchemaDefCSharpPgSqlSchemaObjUsingHandler() {
		if( schemaDefCSharpPgSqlSchemaObjUsingHandler == null ) {
			schemaDefCSharpPgSqlSchemaObjUsingHandler = new CFBamSaxLoaderSchemaDefCSharpPgSqlSchemaObjUsing( this );
		}
		return( schemaDefCSharpPgSqlSchemaObjUsingHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpRamSchemaObjMembers getSchemaDefCSharpRamSchemaObjMembersHandler() {
		if( schemaDefCSharpRamSchemaObjMembersHandler == null ) {
			schemaDefCSharpRamSchemaObjMembersHandler = new CFBamSaxLoaderSchemaDefCSharpRamSchemaObjMembers( this );
		}
		return( schemaDefCSharpRamSchemaObjMembersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpRamSchemaObjImpl getSchemaDefCSharpRamSchemaObjImplHandler() {
		if( schemaDefCSharpRamSchemaObjImplHandler == null ) {
			schemaDefCSharpRamSchemaObjImplHandler = new CFBamSaxLoaderSchemaDefCSharpRamSchemaObjImpl( this );
		}
		return( schemaDefCSharpRamSchemaObjImplHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpRamSchemaObjUsing getSchemaDefCSharpRamSchemaObjUsingHandler() {
		if( schemaDefCSharpRamSchemaObjUsingHandler == null ) {
			schemaDefCSharpRamSchemaObjUsingHandler = new CFBamSaxLoaderSchemaDefCSharpRamSchemaObjUsing( this );
		}
		return( schemaDefCSharpRamSchemaObjUsingHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgSchemaUsing getSchemaDefCSharpXMsgSchemaUsingHandler() {
		if( schemaDefCSharpXMsgSchemaUsingHandler == null ) {
			schemaDefCSharpXMsgSchemaUsingHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgSchemaUsing( this );
		}
		return( schemaDefCSharpXMsgSchemaUsingHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgSchemaFormatters getSchemaDefCSharpXMsgSchemaFormattersHandler() {
		if( schemaDefCSharpXMsgSchemaFormattersHandler == null ) {
			schemaDefCSharpXMsgSchemaFormattersHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgSchemaFormatters( this );
		}
		return( schemaDefCSharpXMsgSchemaFormattersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgClientSchemaUsing getSchemaDefCSharpXMsgClientSchemaUsingHandler() {
		if( schemaDefCSharpXMsgClientSchemaUsingHandler == null ) {
			schemaDefCSharpXMsgClientSchemaUsingHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgClientSchemaUsing( this );
		}
		return( schemaDefCSharpXMsgClientSchemaUsingHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgClientSchemaBody getSchemaDefCSharpXMsgClientSchemaBodyHandler() {
		if( schemaDefCSharpXMsgClientSchemaBodyHandler == null ) {
			schemaDefCSharpXMsgClientSchemaBodyHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgClientSchemaBody( this );
		}
		return( schemaDefCSharpXMsgClientSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaBody getSchemaDefCSharpXMsgRqstSchemaBodyHandler() {
		if( schemaDefCSharpXMsgRqstSchemaBodyHandler == null ) {
			schemaDefCSharpXMsgRqstSchemaBodyHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaBody( this );
		}
		return( schemaDefCSharpXMsgRqstSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaUsing getSchemaDefCSharpXMsgRqstSchemaUsingHandler() {
		if( schemaDefCSharpXMsgRqstSchemaUsingHandler == null ) {
			schemaDefCSharpXMsgRqstSchemaUsingHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaUsing( this );
		}
		return( schemaDefCSharpXMsgRqstSchemaUsingHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaWireParsers getSchemaDefCSharpXMsgRqstSchemaWireParsersHandler() {
		if( schemaDefCSharpXMsgRqstSchemaWireParsersHandler == null ) {
			schemaDefCSharpXMsgRqstSchemaWireParsersHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaWireParsers( this );
		}
		return( schemaDefCSharpXMsgRqstSchemaWireParsersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaXsdSpec getSchemaDefCSharpXMsgRqstSchemaXsdSpecHandler() {
		if( schemaDefCSharpXMsgRqstSchemaXsdSpecHandler == null ) {
			schemaDefCSharpXMsgRqstSchemaXsdSpecHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaXsdSpec( this );
		}
		return( schemaDefCSharpXMsgRqstSchemaXsdSpecHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaXsdElementList getSchemaDefCSharpXMsgRqstSchemaXsdElementListHandler() {
		if( schemaDefCSharpXMsgRqstSchemaXsdElementListHandler == null ) {
			schemaDefCSharpXMsgRqstSchemaXsdElementListHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgRqstSchemaXsdElementList( this );
		}
		return( schemaDefCSharpXMsgRqstSchemaXsdElementListHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaBody getSchemaDefCSharpXMsgRspnSchemaBodyHandler() {
		if( schemaDefCSharpXMsgRspnSchemaBodyHandler == null ) {
			schemaDefCSharpXMsgRspnSchemaBodyHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaBody( this );
		}
		return( schemaDefCSharpXMsgRspnSchemaBodyHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaUsing getSchemaDefCSharpXMsgRspnSchemaUsingHandler() {
		if( schemaDefCSharpXMsgRspnSchemaUsingHandler == null ) {
			schemaDefCSharpXMsgRspnSchemaUsingHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaUsing( this );
		}
		return( schemaDefCSharpXMsgRspnSchemaUsingHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaWireParsers getSchemaDefCSharpXMsgRspnSchemaWireParsersHandler() {
		if( schemaDefCSharpXMsgRspnSchemaWireParsersHandler == null ) {
			schemaDefCSharpXMsgRspnSchemaWireParsersHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaWireParsers( this );
		}
		return( schemaDefCSharpXMsgRspnSchemaWireParsersHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaXsdElementList getSchemaDefCSharpXMsgRspnSchemaXsdElementListHandler() {
		if( schemaDefCSharpXMsgRspnSchemaXsdElementListHandler == null ) {
			schemaDefCSharpXMsgRspnSchemaXsdElementListHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaXsdElementList( this );
		}
		return( schemaDefCSharpXMsgRspnSchemaXsdElementListHandler );
	}
	protected CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaXsdSpec getSchemaDefCSharpXMsgRspnSchemaXsdSpecHandler() {
		if( schemaDefCSharpXMsgRspnSchemaXsdSpecHandler == null ) {
			schemaDefCSharpXMsgRspnSchemaXsdSpecHandler = new CFBamSaxLoaderSchemaDefCSharpXMsgRspnSchemaXsdSpec( this );
		}
		return( schemaDefCSharpXMsgRspnSchemaXsdSpecHandler );
	}
	protected CFBamSaxLoaderSchemaRef getSchemaRefHandler() {
		if( schemaRefHandler == null ) {
			schemaRefHandler = new CFBamSaxLoaderSchemaRef( this );
		}
		return( schemaRefHandler );
	}
	protected CFBamSaxLoaderScope getScopeHandler() {
		if( scopeHandler == null ) {
			scopeHandler = new CFBamSaxLoaderScope( this );
		}
		return( scopeHandler );
	}
	protected CFBamSaxLoaderSecApp getSecAppHandler() {
		if( secAppHandler == null ) {
			secAppHandler = new CFBamSaxLoaderSecApp( this );
			secAppHandler.addElementHandler( "SecForm", getSecFormHandler() );
		}
		return( secAppHandler );
	}
	protected CFBamSaxLoaderSecDevice getSecDeviceHandler() {
		if( secDeviceHandler == null ) {
			secDeviceHandler = new CFBamSaxLoaderSecDevice( this );
		}
		return( secDeviceHandler );
	}
	protected CFBamSaxLoaderSecForm getSecFormHandler() {
		if( secFormHandler == null ) {
			secFormHandler = new CFBamSaxLoaderSecForm( this );
		}
		return( secFormHandler );
	}
	protected CFBamSaxLoaderSecGroup getSecGroupHandler() {
		if( secGroupHandler == null ) {
			secGroupHandler = new CFBamSaxLoaderSecGroup( this );
			secGroupHandler.addElementHandler( "SecGrpInc", getSecGrpIncHandler() );
			secGroupHandler.addElementHandler( "SecGrpMemb", getSecGrpMembHandler() );
			secGroupHandler.addElementHandler( "SecGroupForm", getSecGroupFormHandler() );
		}
		return( secGroupHandler );
	}
	protected CFBamSaxLoaderSecGroupForm getSecGroupFormHandler() {
		if( secGroupFormHandler == null ) {
			secGroupFormHandler = new CFBamSaxLoaderSecGroupForm( this );
		}
		return( secGroupFormHandler );
	}
	protected CFBamSaxLoaderSecGrpInc getSecGrpIncHandler() {
		if( secGrpIncHandler == null ) {
			secGrpIncHandler = new CFBamSaxLoaderSecGrpInc( this );
		}
		return( secGrpIncHandler );
	}
	protected CFBamSaxLoaderSecGrpMemb getSecGrpMembHandler() {
		if( secGrpMembHandler == null ) {
			secGrpMembHandler = new CFBamSaxLoaderSecGrpMemb( this );
		}
		return( secGrpMembHandler );
	}
	protected CFBamSaxLoaderSecSession getSecSessionHandler() {
		if( secSessionHandler == null ) {
			secSessionHandler = new CFBamSaxLoaderSecSession( this );
		}
		return( secSessionHandler );
	}
	protected CFBamSaxLoaderSecUser getSecUserHandler() {
		if( secUserHandler == null ) {
			secUserHandler = new CFBamSaxLoaderSecUser( this );
			secUserHandler.addElementHandler( "SecDevice", getSecDeviceHandler() );
			secUserHandler.addElementHandler( "SecSession", getSecSessionHandler() );
		}
		return( secUserHandler );
	}
	protected CFBamSaxLoaderServerListFunc getServerListFuncHandler() {
		if( serverListFuncHandler == null ) {
			serverListFuncHandler = new CFBamSaxLoaderServerListFunc( this );
			serverListFuncHandler.addElementHandler( "Param", getParamHandler() );
		}
		return( serverListFuncHandler );
	}
	protected CFBamSaxLoaderServerMethod getServerMethodHandler() {
		if( serverMethodHandler == null ) {
			serverMethodHandler = new CFBamSaxLoaderServerMethod( this );
			serverMethodHandler.addElementHandler( "Param", getParamHandler() );
		}
		return( serverMethodHandler );
	}
	protected CFBamSaxLoaderServerObjFunc getServerObjFuncHandler() {
		if( serverObjFuncHandler == null ) {
			serverObjFuncHandler = new CFBamSaxLoaderServerObjFunc( this );
			serverObjFuncHandler.addElementHandler( "Param", getParamHandler() );
		}
		return( serverObjFuncHandler );
	}
	protected CFBamSaxLoaderServerProc getServerProcHandler() {
		if( serverProcHandler == null ) {
			serverProcHandler = new CFBamSaxLoaderServerProc( this );
			serverProcHandler.addElementHandler( "Param", getParamHandler() );
		}
		return( serverProcHandler );
	}
	protected CFBamSaxLoaderService getServiceHandler() {
		if( serviceHandler == null ) {
			serviceHandler = new CFBamSaxLoaderService( this );
		}
		return( serviceHandler );
	}
	protected CFBamSaxLoaderServiceType getServiceTypeHandler() {
		if( serviceTypeHandler == null ) {
			serviceTypeHandler = new CFBamSaxLoaderServiceType( this );
		}
		return( serviceTypeHandler );
	}
	protected CFBamSaxLoaderStringCol getStringColHandler() {
		if( stringColHandler == null ) {
			stringColHandler = new CFBamSaxLoaderStringCol( this );
		}
		return( stringColHandler );
	}
	protected CFBamSaxLoaderStringDef getStringDefHandler() {
		if( stringDefHandler == null ) {
			stringDefHandler = new CFBamSaxLoaderStringDef( this );
		}
		return( stringDefHandler );
	}
	protected CFBamSaxLoaderStringType getStringTypeHandler() {
		if( stringTypeHandler == null ) {
			stringTypeHandler = new CFBamSaxLoaderStringType( this );
		}
		return( stringTypeHandler );
	}
	protected CFBamSaxLoaderSubProject getSubProjectHandler() {
		if( subProjectHandler == null ) {
			subProjectHandler = new CFBamSaxLoaderSubProject( this );
			subProjectHandler.addElementHandler( "MajorVersion", getMajorVersionHandler() );
		}
		return( subProjectHandler );
	}
	protected CFBamSaxLoaderSysCluster getSysClusterHandler() {
		if( sysClusterHandler == null ) {
			sysClusterHandler = new CFBamSaxLoaderSysCluster( this );
		}
		return( sysClusterHandler );
	}
	protected CFBamSaxLoaderTSecGroup getTSecGroupHandler() {
		if( tSecGroupHandler == null ) {
			tSecGroupHandler = new CFBamSaxLoaderTSecGroup( this );
			tSecGroupHandler.addElementHandler( "TSecGrpInc", getTSecGrpIncHandler() );
			tSecGroupHandler.addElementHandler( "TSecGrpMemb", getTSecGrpMembHandler() );
		}
		return( tSecGroupHandler );
	}
	protected CFBamSaxLoaderTSecGrpInc getTSecGrpIncHandler() {
		if( tSecGrpIncHandler == null ) {
			tSecGrpIncHandler = new CFBamSaxLoaderTSecGrpInc( this );
		}
		return( tSecGrpIncHandler );
	}
	protected CFBamSaxLoaderTSecGrpMemb getTSecGrpMembHandler() {
		if( tSecGrpMembHandler == null ) {
			tSecGrpMembHandler = new CFBamSaxLoaderTSecGrpMemb( this );
		}
		return( tSecGrpMembHandler );
	}
	protected CFBamSaxLoaderTZDateCol getTZDateColHandler() {
		if( tZDateColHandler == null ) {
			tZDateColHandler = new CFBamSaxLoaderTZDateCol( this );
		}
		return( tZDateColHandler );
	}
	protected CFBamSaxLoaderTZDateDef getTZDateDefHandler() {
		if( tZDateDefHandler == null ) {
			tZDateDefHandler = new CFBamSaxLoaderTZDateDef( this );
		}
		return( tZDateDefHandler );
	}
	protected CFBamSaxLoaderTZDateType getTZDateTypeHandler() {
		if( tZDateTypeHandler == null ) {
			tZDateTypeHandler = new CFBamSaxLoaderTZDateType( this );
		}
		return( tZDateTypeHandler );
	}
	protected CFBamSaxLoaderTZTimeCol getTZTimeColHandler() {
		if( tZTimeColHandler == null ) {
			tZTimeColHandler = new CFBamSaxLoaderTZTimeCol( this );
		}
		return( tZTimeColHandler );
	}
	protected CFBamSaxLoaderTZTimeDef getTZTimeDefHandler() {
		if( tZTimeDefHandler == null ) {
			tZTimeDefHandler = new CFBamSaxLoaderTZTimeDef( this );
		}
		return( tZTimeDefHandler );
	}
	protected CFBamSaxLoaderTZTimeType getTZTimeTypeHandler() {
		if( tZTimeTypeHandler == null ) {
			tZTimeTypeHandler = new CFBamSaxLoaderTZTimeType( this );
		}
		return( tZTimeTypeHandler );
	}
	protected CFBamSaxLoaderTZTimestampCol getTZTimestampColHandler() {
		if( tZTimestampColHandler == null ) {
			tZTimestampColHandler = new CFBamSaxLoaderTZTimestampCol( this );
		}
		return( tZTimestampColHandler );
	}
	protected CFBamSaxLoaderTZTimestampDef getTZTimestampDefHandler() {
		if( tZTimestampDefHandler == null ) {
			tZTimestampDefHandler = new CFBamSaxLoaderTZTimestampDef( this );
		}
		return( tZTimestampDefHandler );
	}
	protected CFBamSaxLoaderTZTimestampType getTZTimestampTypeHandler() {
		if( tZTimestampTypeHandler == null ) {
			tZTimestampTypeHandler = new CFBamSaxLoaderTZTimestampType( this );
		}
		return( tZTimestampTypeHandler );
	}
	protected CFBamSaxLoaderTable getTableHandler() {
		if( tableHandler == null ) {
			tableHandler = new CFBamSaxLoaderTable( this );
			tableHandler.addElementHandler( "Relation", getRelationHandler() );
			tableHandler.addElementHandler( "Index", getIndexHandler() );
			tableHandler.addElementHandler( "BlobCol", getBlobColHandler() );
			tableHandler.addElementHandler( "BoolCol", getBoolColHandler() );
			tableHandler.addElementHandler( "DateCol", getDateColHandler() );
			tableHandler.addElementHandler( "DoubleCol", getDoubleColHandler() );
			tableHandler.addElementHandler( "FloatCol", getFloatColHandler() );
			tableHandler.addElementHandler( "Int16Col", getInt16ColHandler() );
			tableHandler.addElementHandler( "Int32Col", getInt32ColHandler() );
			tableHandler.addElementHandler( "Int64Col", getInt64ColHandler() );
			tableHandler.addElementHandler( "NmTokenCol", getNmTokenColHandler() );
			tableHandler.addElementHandler( "NmTokensCol", getNmTokensColHandler() );
			tableHandler.addElementHandler( "NumberCol", getNumberColHandler() );
			tableHandler.addElementHandler( "StringCol", getStringColHandler() );
			tableHandler.addElementHandler( "TZDateCol", getTZDateColHandler() );
			tableHandler.addElementHandler( "TZTimeCol", getTZTimeColHandler() );
			tableHandler.addElementHandler( "TZTimestampCol", getTZTimestampColHandler() );
			tableHandler.addElementHandler( "TextCol", getTextColHandler() );
			tableHandler.addElementHandler( "TimeCol", getTimeColHandler() );
			tableHandler.addElementHandler( "TimestampCol", getTimestampColHandler() );
			tableHandler.addElementHandler( "TokenCol", getTokenColHandler() );
			tableHandler.addElementHandler( "UInt16Col", getUInt16ColHandler() );
			tableHandler.addElementHandler( "UInt32Col", getUInt32ColHandler() );
			tableHandler.addElementHandler( "UInt64Col", getUInt64ColHandler() );
			tableHandler.addElementHandler( "UuidCol", getUuidColHandler() );
			tableHandler.addElementHandler( "TableCol", getTableColHandler() );
			tableHandler.addElementHandler( "Chain", getChainHandler() );
			tableHandler.addElementHandler( "DelTopDep", getDelTopDepHandler() );
			tableHandler.addElementHandler( "ClearTopDep", getClearTopDepHandler() );
			tableHandler.addElementHandler( "ServerMethod", getServerMethodHandler() );
			tableHandler.addElementHandler( "ServerObjFunc", getServerObjFuncHandler() );
			tableHandler.addElementHandler( "ServerProc", getServerProcHandler() );
			tableHandler.addElementHandler( "ServerListFunc", getServerListFuncHandler() );
			tableHandler.addElementHandler( "CafeObjMembers", getTableCafeObjMembersHandler() );
			tableHandler.addElementHandler( "CafeObjInterface", getTableCafeObjInterfaceHandler() );
			tableHandler.addElementHandler( "CafeObjImport", getTableCafeObjImportHandler() );
			tableHandler.addElementHandler( "CafeObjImplementation", getTableCafeObjImplementationHandler() );
			tableHandler.addElementHandler( "CafeEditObjMembers", getTableCafeEditObjMembersHandler() );
			tableHandler.addElementHandler( "CafeEditObjInterface", getTableCafeEditObjInterfaceHandler() );
			tableHandler.addElementHandler( "CafeEditObjImport", getTableCafeEditObjImportHandler() );
			tableHandler.addElementHandler( "CafeEditObjImplementation", getTableCafeEditObjImplementationHandler() );
			tableHandler.addElementHandler( "CafeTableImport", getTableCafeTableImportHandler() );
			tableHandler.addElementHandler( "CafeTableMembers", getTableCafeTableMembersHandler() );
			tableHandler.addElementHandler( "CafeTableInterface", getTableCafeTableInterfaceHandler() );
			tableHandler.addElementHandler( "CafeTableImplementation", getTableCafeTableImplementationHandler() );
			tableHandler.addElementHandler( "CafeTableObjImport", getTableCafeTableObjImportHandler() );
			tableHandler.addElementHandler( "CafeTableObjMembers", getTableCafeTableObjMembersHandler() );
			tableHandler.addElementHandler( "CafeTableObjInterface", getTableCafeTableObjInterfaceHandler() );
			tableHandler.addElementHandler( "CafeTableObjImplementation", getTableCafeTableObjImplementationHandler() );
			tableHandler.addElementHandler( "CafeDb2LUWTableImport", getTableCafeDb2LUWTableImportHandler() );
			tableHandler.addElementHandler( "CafeDb2LUWTableMembers", getTableCafeDb2LUWTableMembersHandler() );
			tableHandler.addElementHandler( "CafeDb2LUWTableImplementation", getTableCafeDb2LUWTableImplementationHandler() );
			tableHandler.addElementHandler( "CafeMSSqlTableImport", getTableCafeMSSqlTableImportHandler() );
			tableHandler.addElementHandler( "CafeMSSqlTableMembers", getTableCafeMSSqlTableMembersHandler() );
			tableHandler.addElementHandler( "CafeMSSqlTableImplementation", getTableCafeMSSqlTableImplementationHandler() );
			tableHandler.addElementHandler( "CafeMySqlTableImport", getTableCafeMySqlTableImportHandler() );
			tableHandler.addElementHandler( "CafeMySqlTableMembers", getTableCafeMySqlTableMembersHandler() );
			tableHandler.addElementHandler( "CafeMySqlTableImplementation", getTableCafeMySqlTableImplementationHandler() );
			tableHandler.addElementHandler( "CafeOracleTableImport", getTableCafeOracleTableImportHandler() );
			tableHandler.addElementHandler( "CafeOracleTableMembers", getTableCafeOracleTableMembersHandler() );
			tableHandler.addElementHandler( "CafeOracleTableImplementation", getTableCafeOracleTableImplementationHandler() );
			tableHandler.addElementHandler( "CafePgSqlTableImport", getTableCafePgSqlTableImportHandler() );
			tableHandler.addElementHandler( "CafePgSqlTableMembers", getTableCafePgSqlTableMembersHandler() );
			tableHandler.addElementHandler( "CafePgSqlTableImplementation", getTableCafePgSqlTableImplementationHandler() );
			tableHandler.addElementHandler( "CafeRamTableImport", getTableCafeRamTableImportHandler() );
			tableHandler.addElementHandler( "CafeRamTableMembers", getTableCafeRamTableMembersHandler() );
			tableHandler.addElementHandler( "CafeRamTableImplementation", getTableCafeRamTableImplementationHandler() );
			tableHandler.addElementHandler( "CafeSaxLoaderImport", getTableCafeSaxLoaderImportHandler() );
			tableHandler.addElementHandler( "CafeSaxLoaderStartElement", getTableCafeSaxLoaderStartElementHandler() );
			tableHandler.addElementHandler( "CafeSaxLoaderEndElement", getTableCafeSaxLoaderEndElementHandler() );
			tableHandler.addElementHandler( "CafeXMsgTableImport", getTableCafeXMsgTableImportHandler() );
			tableHandler.addElementHandler( "CafeXMsgTableformatters", getTableCafeXMsgTableformattersHandler() );
			tableHandler.addElementHandler( "CafeXMsgRqstTableImport", getTableCafeXMsgRqstTableImportHandler() );
			tableHandler.addElementHandler( "CafeXMsgRspnTableImport", getTableCafeXMsgRspnTableImportHandler() );
			tableHandler.addElementHandler( "CafeXMsgClientTableImport", getTableCafeXMsgClientTableImportHandler() );
			tableHandler.addElementHandler( "CafeXMsgRqstTableBody", getTableCafeXMsgRqstTableBodyHandler() );
			tableHandler.addElementHandler( "CafeXMsgRspnTableBody", getTableCafeXMsgRspnTableBodyHandler() );
			tableHandler.addElementHandler( "CafeXMsgClientTableBody", getTableCafeXMsgClientTableBodyHandler() );
			tableHandler.addElementHandler( "CPlusObjMembers", getTableCPlusObjMembersHandler() );
			tableHandler.addElementHandler( "CPlusObjInterface", getTableCPlusObjInterfaceHandler() );
			tableHandler.addElementHandler( "CPlusObjInclude", getTableCPlusObjIncludeHandler() );
			tableHandler.addElementHandler( "CPlusObjImplementation", getTableCPlusObjImplementationHandler() );
			tableHandler.addElementHandler( "CPlusEditObjMembers", getTableCPlusEditObjMembersHandler() );
			tableHandler.addElementHandler( "CPlusEditObjInterace", getTableCPlusEditObjInteraceHandler() );
			tableHandler.addElementHandler( "CPlusEditObjInclude", getTableCPlusEditObjIncludeHandler() );
			tableHandler.addElementHandler( "CPlusEditObjImplementation", getTableCPlusEditObjImplementationHandler() );
			tableHandler.addElementHandler( "CPlusTableInclude", getTableCPlusTableIncludeHandler() );
			tableHandler.addElementHandler( "CPlusTableMembers", getTableCPlusTableMembersHandler() );
			tableHandler.addElementHandler( "CPlusTableInterface", getTableCPlusTableInterfaceHandler() );
			tableHandler.addElementHandler( "CPlusTableImplementation", getTableCPlusTableImplementationHandler() );
			tableHandler.addElementHandler( "CPlusTableObjInclude", getTableCPlusTableObjIncludeHandler() );
			tableHandler.addElementHandler( "CPlusTableObjMembers", getTableCPlusTableObjMembersHandler() );
			tableHandler.addElementHandler( "CPlusTableObjInterface", getTableCPlusTableObjInterfaceHandler() );
			tableHandler.addElementHandler( "CPlusTableObjImplementation", getTableCPlusTableObjImplementationHandler() );
			tableHandler.addElementHandler( "CPlusDb2LUWTableInclude", getTableCPlusDb2LUWTableIncludeHandler() );
			tableHandler.addElementHandler( "CPlusDb2LUWTableMembers", getTableCPlusDb2LUWTableMembersHandler() );
			tableHandler.addElementHandler( "CPlusDb2LUWTableImplementation", getTableCPlusDb2LUWTableImplementationHandler() );
			tableHandler.addElementHandler( "CPlusMSSqlTableInclude", getTableCPlusMSSqlTableIncludeHandler() );
			tableHandler.addElementHandler( "CPlusMSSqlTableMembers", getTableCPlusMSSqlTableMembersHandler() );
			tableHandler.addElementHandler( "CPlusMSSqlTableImplementation", getTableCPlusMSSqlTableImplementationHandler() );
			tableHandler.addElementHandler( "CPlusMySqlTableInclude", getTableCPlusMySqlTableIncludeHandler() );
			tableHandler.addElementHandler( "CPlusMySqlTableMembers", getTableCPlusMySqlTableMembersHandler() );
			tableHandler.addElementHandler( "CPlusMySqlTableImplementation", getTableCPlusMySqlTableImplementationHandler() );
			tableHandler.addElementHandler( "CPlusOracleTableInclude", getTableCPlusOracleTableIncludeHandler() );
			tableHandler.addElementHandler( "CPlusOracleTableMembers", getTableCPlusOracleTableMembersHandler() );
			tableHandler.addElementHandler( "CPlusOracleTableImplementation", getTableCPlusOracleTableImplementationHandler() );
			tableHandler.addElementHandler( "CPlusPgSqlTableMembers", getTableCPlusPgSqlTableMembersHandler() );
			tableHandler.addElementHandler( "CPlusPgSqlTableImplementation", getTableCPlusPgSqlTableImplementationHandler() );
			tableHandler.addElementHandler( "CPlusRamTableInclude", getTableCPlusRamTableIncludeHandler() );
			tableHandler.addElementHandler( "CPlusRamTableMembers", getTableCPlusRamTableMembersHandler() );
			tableHandler.addElementHandler( "CPlusRamTableImplementation", getTableCPlusRamTableImplementationHandler() );
			tableHandler.addElementHandler( "CPlusSaxLoaderInclude", getTableCPlusSaxLoaderIncludeHandler() );
			tableHandler.addElementHandler( "CPlusSaxLoaderStartElement", getTableCPlusSaxLoaderStartElementHandler() );
			tableHandler.addElementHandler( "CPlusSaxLoaderEndElement", getTableCPlusSaxLoaderEndElementHandler() );
			tableHandler.addElementHandler( "CPlusXMsgTableInclude", getTableCPlusXMsgTableIncludeHandler() );
			tableHandler.addElementHandler( "CPlusXMsgTableFormatters", getTableCPlusXMsgTableFormattersHandler() );
			tableHandler.addElementHandler( "CPlusXMsgRqstTableInclude", getTableCPlusXMsgRqstTableIncludeHandler() );
			tableHandler.addElementHandler( "CPlusXMsgRspnTableInclude", getTableCPlusXMsgRspnTableIncludeHandler() );
			tableHandler.addElementHandler( "CPlusXMsgClientTableInclude", getTableCPlusXMsgClientTableIncludeHandler() );
			tableHandler.addElementHandler( "CPlusXMsgRqstTableBody", getTableCPlusXMsgRqstTableBodyHandler() );
			tableHandler.addElementHandler( "CPlusXMsgRspnTableBody", getTableCPlusXMsgRspnTableBodyHandler() );
			tableHandler.addElementHandler( "CPlusXMsgClientTableBody", getTableCPlusXMsgClientTableBodyHandler() );
			tableHandler.addElementHandler( "HPlusObjMembers", getTableHPlusObjMembersHandler() );
			tableHandler.addElementHandler( "HPlusObjInterface", getTableHPlusObjInterfaceHandler() );
			tableHandler.addElementHandler( "HPlusObjInclude", getTableHPlusObjIncludeHandler() );
			tableHandler.addElementHandler( "HPlusObjImplemntation", getTableHPlusObjImplemntationHandler() );
			tableHandler.addElementHandler( "HPlusEditObjMembers", getTableHPlusEditObjMembersHandler() );
			tableHandler.addElementHandler( "HPlusEditObjInterface", getTableHPlusEditObjInterfaceHandler() );
			tableHandler.addElementHandler( "HPlusEditObjInclude", getTableHPlusEditObjIncludeHandler() );
			tableHandler.addElementHandler( "HPlusEditObjImplementation", getTableHPlusEditObjImplementationHandler() );
			tableHandler.addElementHandler( "HPlusTableInclude", getTableHPlusTableIncludeHandler() );
			tableHandler.addElementHandler( "HPlusTableMembers", getTableHPlusTableMembersHandler() );
			tableHandler.addElementHandler( "HPlusTableInterface", getTableHPlusTableInterfaceHandler() );
			tableHandler.addElementHandler( "HPlusTableImplementation", getTableHPlusTableImplementationHandler() );
			tableHandler.addElementHandler( "HPlusTableObjInclude", getTableHPlusTableObjIncludeHandler() );
			tableHandler.addElementHandler( "HPlusTableObjMembers", getTableHPlusTableObjMembersHandler() );
			tableHandler.addElementHandler( "HPlusTableObjInterface", getTableHPlusTableObjInterfaceHandler() );
			tableHandler.addElementHandler( "HPlusTableObjImplementation", getTableHPlusTableObjImplementationHandler() );
			tableHandler.addElementHandler( "HPlusDb2LUWTableInclude", getTableHPlusDb2LUWTableIncludeHandler() );
			tableHandler.addElementHandler( "HPlusDb2LUWTableMembers", getTableHPlusDb2LUWTableMembersHandler() );
			tableHandler.addElementHandler( "HPlusDb2LUWTableImplementation", getTableHPlusDb2LUWTableImplementationHandler() );
			tableHandler.addElementHandler( "HPlusMSSqlTableInclude", getTableHPlusMSSqlTableIncludeHandler() );
			tableHandler.addElementHandler( "HPlusMSSqlTableMembers", getTableHPlusMSSqlTableMembersHandler() );
			tableHandler.addElementHandler( "HPlusMSSqlTableImplementation", getTableHPlusMSSqlTableImplementationHandler() );
			tableHandler.addElementHandler( "HPlusMySqlTableInclude", getTableHPlusMySqlTableIncludeHandler() );
			tableHandler.addElementHandler( "HPlusMySqlTableMembers", getTableHPlusMySqlTableMembersHandler() );
			tableHandler.addElementHandler( "HPlusMySqlTableImplementation", getTableHPlusMySqlTableImplementationHandler() );
			tableHandler.addElementHandler( "HPlusOracleTableInclude", getTableHPlusOracleTableIncludeHandler() );
			tableHandler.addElementHandler( "HPlusOracleTableMembers", getTableHPlusOracleTableMembersHandler() );
			tableHandler.addElementHandler( "HPlusOracleTableImplementation", getTableHPlusOracleTableImplementationHandler() );
			tableHandler.addElementHandler( "HPlusPgSqlTableInclude", getTableHPlusPgSqlTableIncludeHandler() );
			tableHandler.addElementHandler( "HPlusPgSqlTableMembers", getTableHPlusPgSqlTableMembersHandler() );
			tableHandler.addElementHandler( "HPlusPgSqlTableImplementation", getTableHPlusPgSqlTableImplementationHandler() );
			tableHandler.addElementHandler( "HPlusRamTableInclude", getTableHPlusRamTableIncludeHandler() );
			tableHandler.addElementHandler( "HPlusRamTableMembers", getTableHPlusRamTableMembersHandler() );
			tableHandler.addElementHandler( "HPlusRamTableImplementation", getTableHPlusRamTableImplementationHandler() );
			tableHandler.addElementHandler( "HPlusSaxLoaderInclude", getTableHPlusSaxLoaderIncludeHandler() );
			tableHandler.addElementHandler( "HPlusSaxLoaderStartElement", getTableHPlusSaxLoaderStartElementHandler() );
			tableHandler.addElementHandler( "HPlusSaxLoaderEndElement", getTableHPlusSaxLoaderEndElementHandler() );
			tableHandler.addElementHandler( "HPlusXMsgTableInclude", getTableHPlusXMsgTableIncludeHandler() );
			tableHandler.addElementHandler( "HPlusXMsgTableFormatters", getTableHPlusXMsgTableFormattersHandler() );
			tableHandler.addElementHandler( "HPlusXMsgRqstTableInclude", getTableHPlusXMsgRqstTableIncludeHandler() );
			tableHandler.addElementHandler( "HPlusXMsgRspnTableInclude", getTableHPlusXMsgRspnTableIncludeHandler() );
			tableHandler.addElementHandler( "HPlusXMsgClientTableInclude", getTableHPlusXMsgClientTableIncludeHandler() );
			tableHandler.addElementHandler( "HPlusXMsgRqstTableBody", getTableHPlusXMsgRqstTableBodyHandler() );
			tableHandler.addElementHandler( "HPlusXMsgRspnTableBody", getTableHPlusXMsgRspnTableBodyHandler() );
			tableHandler.addElementHandler( "HPlusXMsgClientTableBody", getTableHPlusXMsgClientTableBodyHandler() );
			tableHandler.addElementHandler( "CSharpObjMembers", getTableCSharpObjMembersHandler() );
			tableHandler.addElementHandler( "CSharpObjInterface", getTableCSharpObjInterfaceHandler() );
			tableHandler.addElementHandler( "CSharpObjUsing", getTableCSharpObjUsingHandler() );
			tableHandler.addElementHandler( "CSharpObjImplementation", getTableCSharpObjImplementationHandler() );
			tableHandler.addElementHandler( "CSharpEditObjMembers", getTableCSharpEditObjMembersHandler() );
			tableHandler.addElementHandler( "CSharpEditObjInterface", getTableCSharpEditObjInterfaceHandler() );
			tableHandler.addElementHandler( "CSharpEditObjUsing", getTableCSharpEditObjUsingHandler() );
			tableHandler.addElementHandler( "CSharpEditObjImplementation", getTableCSharpEditObjImplementationHandler() );
			tableHandler.addElementHandler( "CSharpTableUsing", getTableCSharpTableUsingHandler() );
			tableHandler.addElementHandler( "CSharpTableMembers", getTableCSharpTableMembersHandler() );
			tableHandler.addElementHandler( "CSharpTableInterface", getTableCSharpTableInterfaceHandler() );
			tableHandler.addElementHandler( "CSharpTableImplementation", getTableCSharpTableImplementationHandler() );
			tableHandler.addElementHandler( "CSharpTableObjUsing", getTableCSharpTableObjUsingHandler() );
			tableHandler.addElementHandler( "CSharpTableObjMembers", getTableCSharpTableObjMembersHandler() );
			tableHandler.addElementHandler( "CSharpTableObjInterface", getTableCSharpTableObjInterfaceHandler() );
			tableHandler.addElementHandler( "CSharpTableObjImplementation", getTableCSharpTableObjImplementationHandler() );
			tableHandler.addElementHandler( "CSharpDb2LUWTableUsing", getTableCSharpDb2LUWTableUsingHandler() );
			tableHandler.addElementHandler( "CSharpDb2LUWTableMembers", getTableCSharpDb2LUWTableMembersHandler() );
			tableHandler.addElementHandler( "CSharpDb2LUWTableImplementation", getTableCSharpDb2LUWTableImplementationHandler() );
			tableHandler.addElementHandler( "CSharpMSSqlTableUsing", getTableCSharpMSSqlTableUsingHandler() );
			tableHandler.addElementHandler( "CSharpMSSqlTableMembers", getTableCSharpMSSqlTableMembersHandler() );
			tableHandler.addElementHandler( "CSharpMSSqlTableImplementation", getTableCSharpMSSqlTableImplementationHandler() );
			tableHandler.addElementHandler( "CSharpMySqlTableUsing", getTableCSharpMySqlTableUsingHandler() );
			tableHandler.addElementHandler( "CSharpMySqlTableMembers", getTableCSharpMySqlTableMembersHandler() );
			tableHandler.addElementHandler( "CSharpMySqlTableImplementation", getTableCSharpMySqlTableImplementationHandler() );
			tableHandler.addElementHandler( "CSharpOracleTableUsing", getTableCSharpOracleTableUsingHandler() );
			tableHandler.addElementHandler( "CSharpOracleTableMembers", getTableCSharpOracleTableMembersHandler() );
			tableHandler.addElementHandler( "CSharpOracleTableImplementation", getTableCSharpOracleTableImplementationHandler() );
			tableHandler.addElementHandler( "CSharpPgSqlTableUsing", getTableCSharpPgSqlTableUsingHandler() );
			tableHandler.addElementHandler( "CSharpPgSqlTableMembers", getTableCSharpPgSqlTableMembersHandler() );
			tableHandler.addElementHandler( "CSharpPgSqlTableImplementation", getTableCSharpPgSqlTableImplementationHandler() );
			tableHandler.addElementHandler( "CSharpRamTableUsing", getTableCSharpRamTableUsingHandler() );
			tableHandler.addElementHandler( "CSharpRamTableMembers", getTableCSharpRamTableMembersHandler() );
			tableHandler.addElementHandler( "CSharpRamTableImplementation", getTableCSharpRamTableImplementationHandler() );
			tableHandler.addElementHandler( "CSharpSaxLoaderUsing", getTableCSharpSaxLoaderUsingHandler() );
			tableHandler.addElementHandler( "CSharpSaxLoaderStartElement", getTableCSharpSaxLoaderStartElementHandler() );
			tableHandler.addElementHandler( "CSharpSaxLoaderEndElement", getTableCSharpSaxLoaderEndElementHandler() );
			tableHandler.addElementHandler( "CSharpXMsgTableUsing", getTableCSharpXMsgTableUsingHandler() );
			tableHandler.addElementHandler( "CSharpXMsgTableFormatters", getTableCSharpXMsgTableFormattersHandler() );
			tableHandler.addElementHandler( "CSharpXMsgRqstTableUsing", getTableCSharpXMsgRqstTableUsingHandler() );
			tableHandler.addElementHandler( "CSharpXMsgRspnTableUsing", getTableCSharpXMsgRspnTableUsingHandler() );
			tableHandler.addElementHandler( "CSharpXMsgClientTableUsing", getTableCSharpXMsgClientTableUsingHandler() );
			tableHandler.addElementHandler( "CSharpXMsgRqstTableBody", getTableCSharpXMsgRqstTableBodyHandler() );
			tableHandler.addElementHandler( "CSharpXMsgRspnTableBody", getTableCSharpXMsgRspnTableBodyHandler() );
			tableHandler.addElementHandler( "CSharpXMsgClientTableBody", getTableCSharpXMsgClientTableBodyHandler() );
		}
		return( tableHandler );
	}
	protected CFBamSaxLoaderTableCafeObjMembers getTableCafeObjMembersHandler() {
		if( tableCafeObjMembersHandler == null ) {
			tableCafeObjMembersHandler = new CFBamSaxLoaderTableCafeObjMembers( this );
		}
		return( tableCafeObjMembersHandler );
	}
	protected CFBamSaxLoaderTableCafeObjInterface getTableCafeObjInterfaceHandler() {
		if( tableCafeObjInterfaceHandler == null ) {
			tableCafeObjInterfaceHandler = new CFBamSaxLoaderTableCafeObjInterface( this );
		}
		return( tableCafeObjInterfaceHandler );
	}
	protected CFBamSaxLoaderTableCafeObjImport getTableCafeObjImportHandler() {
		if( tableCafeObjImportHandler == null ) {
			tableCafeObjImportHandler = new CFBamSaxLoaderTableCafeObjImport( this );
		}
		return( tableCafeObjImportHandler );
	}
	protected CFBamSaxLoaderTableCafeObjImplementation getTableCafeObjImplementationHandler() {
		if( tableCafeObjImplementationHandler == null ) {
			tableCafeObjImplementationHandler = new CFBamSaxLoaderTableCafeObjImplementation( this );
		}
		return( tableCafeObjImplementationHandler );
	}
	protected CFBamSaxLoaderTableCafeEditObjMembers getTableCafeEditObjMembersHandler() {
		if( tableCafeEditObjMembersHandler == null ) {
			tableCafeEditObjMembersHandler = new CFBamSaxLoaderTableCafeEditObjMembers( this );
		}
		return( tableCafeEditObjMembersHandler );
	}
	protected CFBamSaxLoaderTableCafeEditObjInterface getTableCafeEditObjInterfaceHandler() {
		if( tableCafeEditObjInterfaceHandler == null ) {
			tableCafeEditObjInterfaceHandler = new CFBamSaxLoaderTableCafeEditObjInterface( this );
		}
		return( tableCafeEditObjInterfaceHandler );
	}
	protected CFBamSaxLoaderTableCafeEditObjImport getTableCafeEditObjImportHandler() {
		if( tableCafeEditObjImportHandler == null ) {
			tableCafeEditObjImportHandler = new CFBamSaxLoaderTableCafeEditObjImport( this );
		}
		return( tableCafeEditObjImportHandler );
	}
	protected CFBamSaxLoaderTableCafeEditObjImplementation getTableCafeEditObjImplementationHandler() {
		if( tableCafeEditObjImplementationHandler == null ) {
			tableCafeEditObjImplementationHandler = new CFBamSaxLoaderTableCafeEditObjImplementation( this );
		}
		return( tableCafeEditObjImplementationHandler );
	}
	protected CFBamSaxLoaderTableCafeTableImport getTableCafeTableImportHandler() {
		if( tableCafeTableImportHandler == null ) {
			tableCafeTableImportHandler = new CFBamSaxLoaderTableCafeTableImport( this );
		}
		return( tableCafeTableImportHandler );
	}
	protected CFBamSaxLoaderTableCafeTableMembers getTableCafeTableMembersHandler() {
		if( tableCafeTableMembersHandler == null ) {
			tableCafeTableMembersHandler = new CFBamSaxLoaderTableCafeTableMembers( this );
		}
		return( tableCafeTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCafeTableInterface getTableCafeTableInterfaceHandler() {
		if( tableCafeTableInterfaceHandler == null ) {
			tableCafeTableInterfaceHandler = new CFBamSaxLoaderTableCafeTableInterface( this );
		}
		return( tableCafeTableInterfaceHandler );
	}
	protected CFBamSaxLoaderTableCafeTableImplementation getTableCafeTableImplementationHandler() {
		if( tableCafeTableImplementationHandler == null ) {
			tableCafeTableImplementationHandler = new CFBamSaxLoaderTableCafeTableImplementation( this );
		}
		return( tableCafeTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCafeTableObjImport getTableCafeTableObjImportHandler() {
		if( tableCafeTableObjImportHandler == null ) {
			tableCafeTableObjImportHandler = new CFBamSaxLoaderTableCafeTableObjImport( this );
		}
		return( tableCafeTableObjImportHandler );
	}
	protected CFBamSaxLoaderTableCafeTableObjMembers getTableCafeTableObjMembersHandler() {
		if( tableCafeTableObjMembersHandler == null ) {
			tableCafeTableObjMembersHandler = new CFBamSaxLoaderTableCafeTableObjMembers( this );
		}
		return( tableCafeTableObjMembersHandler );
	}
	protected CFBamSaxLoaderTableCafeTableObjInterface getTableCafeTableObjInterfaceHandler() {
		if( tableCafeTableObjInterfaceHandler == null ) {
			tableCafeTableObjInterfaceHandler = new CFBamSaxLoaderTableCafeTableObjInterface( this );
		}
		return( tableCafeTableObjInterfaceHandler );
	}
	protected CFBamSaxLoaderTableCafeTableObjImplementation getTableCafeTableObjImplementationHandler() {
		if( tableCafeTableObjImplementationHandler == null ) {
			tableCafeTableObjImplementationHandler = new CFBamSaxLoaderTableCafeTableObjImplementation( this );
		}
		return( tableCafeTableObjImplementationHandler );
	}
	protected CFBamSaxLoaderTableCafeDb2LUWTableImport getTableCafeDb2LUWTableImportHandler() {
		if( tableCafeDb2LUWTableImportHandler == null ) {
			tableCafeDb2LUWTableImportHandler = new CFBamSaxLoaderTableCafeDb2LUWTableImport( this );
		}
		return( tableCafeDb2LUWTableImportHandler );
	}
	protected CFBamSaxLoaderTableCafeDb2LUWTableMembers getTableCafeDb2LUWTableMembersHandler() {
		if( tableCafeDb2LUWTableMembersHandler == null ) {
			tableCafeDb2LUWTableMembersHandler = new CFBamSaxLoaderTableCafeDb2LUWTableMembers( this );
		}
		return( tableCafeDb2LUWTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCafeDb2LUWTableImplementation getTableCafeDb2LUWTableImplementationHandler() {
		if( tableCafeDb2LUWTableImplementationHandler == null ) {
			tableCafeDb2LUWTableImplementationHandler = new CFBamSaxLoaderTableCafeDb2LUWTableImplementation( this );
		}
		return( tableCafeDb2LUWTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCafeMSSqlTableImport getTableCafeMSSqlTableImportHandler() {
		if( tableCafeMSSqlTableImportHandler == null ) {
			tableCafeMSSqlTableImportHandler = new CFBamSaxLoaderTableCafeMSSqlTableImport( this );
		}
		return( tableCafeMSSqlTableImportHandler );
	}
	protected CFBamSaxLoaderTableCafeMSSqlTableMembers getTableCafeMSSqlTableMembersHandler() {
		if( tableCafeMSSqlTableMembersHandler == null ) {
			tableCafeMSSqlTableMembersHandler = new CFBamSaxLoaderTableCafeMSSqlTableMembers( this );
		}
		return( tableCafeMSSqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCafeMSSqlTableImplementation getTableCafeMSSqlTableImplementationHandler() {
		if( tableCafeMSSqlTableImplementationHandler == null ) {
			tableCafeMSSqlTableImplementationHandler = new CFBamSaxLoaderTableCafeMSSqlTableImplementation( this );
		}
		return( tableCafeMSSqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCafeMySqlTableImport getTableCafeMySqlTableImportHandler() {
		if( tableCafeMySqlTableImportHandler == null ) {
			tableCafeMySqlTableImportHandler = new CFBamSaxLoaderTableCafeMySqlTableImport( this );
		}
		return( tableCafeMySqlTableImportHandler );
	}
	protected CFBamSaxLoaderTableCafeMySqlTableMembers getTableCafeMySqlTableMembersHandler() {
		if( tableCafeMySqlTableMembersHandler == null ) {
			tableCafeMySqlTableMembersHandler = new CFBamSaxLoaderTableCafeMySqlTableMembers( this );
		}
		return( tableCafeMySqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCafeMySqlTableImplementation getTableCafeMySqlTableImplementationHandler() {
		if( tableCafeMySqlTableImplementationHandler == null ) {
			tableCafeMySqlTableImplementationHandler = new CFBamSaxLoaderTableCafeMySqlTableImplementation( this );
		}
		return( tableCafeMySqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCafeOracleTableImport getTableCafeOracleTableImportHandler() {
		if( tableCafeOracleTableImportHandler == null ) {
			tableCafeOracleTableImportHandler = new CFBamSaxLoaderTableCafeOracleTableImport( this );
		}
		return( tableCafeOracleTableImportHandler );
	}
	protected CFBamSaxLoaderTableCafeOracleTableMembers getTableCafeOracleTableMembersHandler() {
		if( tableCafeOracleTableMembersHandler == null ) {
			tableCafeOracleTableMembersHandler = new CFBamSaxLoaderTableCafeOracleTableMembers( this );
		}
		return( tableCafeOracleTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCafeOracleTableImplementation getTableCafeOracleTableImplementationHandler() {
		if( tableCafeOracleTableImplementationHandler == null ) {
			tableCafeOracleTableImplementationHandler = new CFBamSaxLoaderTableCafeOracleTableImplementation( this );
		}
		return( tableCafeOracleTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCafePgSqlTableImport getTableCafePgSqlTableImportHandler() {
		if( tableCafePgSqlTableImportHandler == null ) {
			tableCafePgSqlTableImportHandler = new CFBamSaxLoaderTableCafePgSqlTableImport( this );
		}
		return( tableCafePgSqlTableImportHandler );
	}
	protected CFBamSaxLoaderTableCafePgSqlTableMembers getTableCafePgSqlTableMembersHandler() {
		if( tableCafePgSqlTableMembersHandler == null ) {
			tableCafePgSqlTableMembersHandler = new CFBamSaxLoaderTableCafePgSqlTableMembers( this );
		}
		return( tableCafePgSqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCafePgSqlTableImplementation getTableCafePgSqlTableImplementationHandler() {
		if( tableCafePgSqlTableImplementationHandler == null ) {
			tableCafePgSqlTableImplementationHandler = new CFBamSaxLoaderTableCafePgSqlTableImplementation( this );
		}
		return( tableCafePgSqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCafeRamTableImport getTableCafeRamTableImportHandler() {
		if( tableCafeRamTableImportHandler == null ) {
			tableCafeRamTableImportHandler = new CFBamSaxLoaderTableCafeRamTableImport( this );
		}
		return( tableCafeRamTableImportHandler );
	}
	protected CFBamSaxLoaderTableCafeRamTableMembers getTableCafeRamTableMembersHandler() {
		if( tableCafeRamTableMembersHandler == null ) {
			tableCafeRamTableMembersHandler = new CFBamSaxLoaderTableCafeRamTableMembers( this );
		}
		return( tableCafeRamTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCafeRamTableImplementation getTableCafeRamTableImplementationHandler() {
		if( tableCafeRamTableImplementationHandler == null ) {
			tableCafeRamTableImplementationHandler = new CFBamSaxLoaderTableCafeRamTableImplementation( this );
		}
		return( tableCafeRamTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCafeSaxLoaderImport getTableCafeSaxLoaderImportHandler() {
		if( tableCafeSaxLoaderImportHandler == null ) {
			tableCafeSaxLoaderImportHandler = new CFBamSaxLoaderTableCafeSaxLoaderImport( this );
		}
		return( tableCafeSaxLoaderImportHandler );
	}
	protected CFBamSaxLoaderTableCafeSaxLoaderStartElement getTableCafeSaxLoaderStartElementHandler() {
		if( tableCafeSaxLoaderStartElementHandler == null ) {
			tableCafeSaxLoaderStartElementHandler = new CFBamSaxLoaderTableCafeSaxLoaderStartElement( this );
		}
		return( tableCafeSaxLoaderStartElementHandler );
	}
	protected CFBamSaxLoaderTableCafeSaxLoaderEndElement getTableCafeSaxLoaderEndElementHandler() {
		if( tableCafeSaxLoaderEndElementHandler == null ) {
			tableCafeSaxLoaderEndElementHandler = new CFBamSaxLoaderTableCafeSaxLoaderEndElement( this );
		}
		return( tableCafeSaxLoaderEndElementHandler );
	}
	protected CFBamSaxLoaderTableCafeXMsgTableImport getTableCafeXMsgTableImportHandler() {
		if( tableCafeXMsgTableImportHandler == null ) {
			tableCafeXMsgTableImportHandler = new CFBamSaxLoaderTableCafeXMsgTableImport( this );
		}
		return( tableCafeXMsgTableImportHandler );
	}
	protected CFBamSaxLoaderTableCafeXMsgTableformatters getTableCafeXMsgTableformattersHandler() {
		if( tableCafeXMsgTableformattersHandler == null ) {
			tableCafeXMsgTableformattersHandler = new CFBamSaxLoaderTableCafeXMsgTableformatters( this );
		}
		return( tableCafeXMsgTableformattersHandler );
	}
	protected CFBamSaxLoaderTableCafeXMsgRqstTableImport getTableCafeXMsgRqstTableImportHandler() {
		if( tableCafeXMsgRqstTableImportHandler == null ) {
			tableCafeXMsgRqstTableImportHandler = new CFBamSaxLoaderTableCafeXMsgRqstTableImport( this );
		}
		return( tableCafeXMsgRqstTableImportHandler );
	}
	protected CFBamSaxLoaderTableCafeXMsgRspnTableImport getTableCafeXMsgRspnTableImportHandler() {
		if( tableCafeXMsgRspnTableImportHandler == null ) {
			tableCafeXMsgRspnTableImportHandler = new CFBamSaxLoaderTableCafeXMsgRspnTableImport( this );
		}
		return( tableCafeXMsgRspnTableImportHandler );
	}
	protected CFBamSaxLoaderTableCafeXMsgClientTableImport getTableCafeXMsgClientTableImportHandler() {
		if( tableCafeXMsgClientTableImportHandler == null ) {
			tableCafeXMsgClientTableImportHandler = new CFBamSaxLoaderTableCafeXMsgClientTableImport( this );
		}
		return( tableCafeXMsgClientTableImportHandler );
	}
	protected CFBamSaxLoaderTableCafeXMsgRqstTableBody getTableCafeXMsgRqstTableBodyHandler() {
		if( tableCafeXMsgRqstTableBodyHandler == null ) {
			tableCafeXMsgRqstTableBodyHandler = new CFBamSaxLoaderTableCafeXMsgRqstTableBody( this );
		}
		return( tableCafeXMsgRqstTableBodyHandler );
	}
	protected CFBamSaxLoaderTableCafeXMsgRspnTableBody getTableCafeXMsgRspnTableBodyHandler() {
		if( tableCafeXMsgRspnTableBodyHandler == null ) {
			tableCafeXMsgRspnTableBodyHandler = new CFBamSaxLoaderTableCafeXMsgRspnTableBody( this );
		}
		return( tableCafeXMsgRspnTableBodyHandler );
	}
	protected CFBamSaxLoaderTableCafeXMsgClientTableBody getTableCafeXMsgClientTableBodyHandler() {
		if( tableCafeXMsgClientTableBodyHandler == null ) {
			tableCafeXMsgClientTableBodyHandler = new CFBamSaxLoaderTableCafeXMsgClientTableBody( this );
		}
		return( tableCafeXMsgClientTableBodyHandler );
	}
	protected CFBamSaxLoaderTableCPlusObjMembers getTableCPlusObjMembersHandler() {
		if( tableCPlusObjMembersHandler == null ) {
			tableCPlusObjMembersHandler = new CFBamSaxLoaderTableCPlusObjMembers( this );
		}
		return( tableCPlusObjMembersHandler );
	}
	protected CFBamSaxLoaderTableCPlusObjInterface getTableCPlusObjInterfaceHandler() {
		if( tableCPlusObjInterfaceHandler == null ) {
			tableCPlusObjInterfaceHandler = new CFBamSaxLoaderTableCPlusObjInterface( this );
		}
		return( tableCPlusObjInterfaceHandler );
	}
	protected CFBamSaxLoaderTableCPlusObjInclude getTableCPlusObjIncludeHandler() {
		if( tableCPlusObjIncludeHandler == null ) {
			tableCPlusObjIncludeHandler = new CFBamSaxLoaderTableCPlusObjInclude( this );
		}
		return( tableCPlusObjIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusObjImplementation getTableCPlusObjImplementationHandler() {
		if( tableCPlusObjImplementationHandler == null ) {
			tableCPlusObjImplementationHandler = new CFBamSaxLoaderTableCPlusObjImplementation( this );
		}
		return( tableCPlusObjImplementationHandler );
	}
	protected CFBamSaxLoaderTableCPlusEditObjMembers getTableCPlusEditObjMembersHandler() {
		if( tableCPlusEditObjMembersHandler == null ) {
			tableCPlusEditObjMembersHandler = new CFBamSaxLoaderTableCPlusEditObjMembers( this );
		}
		return( tableCPlusEditObjMembersHandler );
	}
	protected CFBamSaxLoaderTableCPlusEditObjInterace getTableCPlusEditObjInteraceHandler() {
		if( tableCPlusEditObjInteraceHandler == null ) {
			tableCPlusEditObjInteraceHandler = new CFBamSaxLoaderTableCPlusEditObjInterace( this );
		}
		return( tableCPlusEditObjInteraceHandler );
	}
	protected CFBamSaxLoaderTableCPlusEditObjInclude getTableCPlusEditObjIncludeHandler() {
		if( tableCPlusEditObjIncludeHandler == null ) {
			tableCPlusEditObjIncludeHandler = new CFBamSaxLoaderTableCPlusEditObjInclude( this );
		}
		return( tableCPlusEditObjIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusEditObjImplementation getTableCPlusEditObjImplementationHandler() {
		if( tableCPlusEditObjImplementationHandler == null ) {
			tableCPlusEditObjImplementationHandler = new CFBamSaxLoaderTableCPlusEditObjImplementation( this );
		}
		return( tableCPlusEditObjImplementationHandler );
	}
	protected CFBamSaxLoaderTableCPlusTableInclude getTableCPlusTableIncludeHandler() {
		if( tableCPlusTableIncludeHandler == null ) {
			tableCPlusTableIncludeHandler = new CFBamSaxLoaderTableCPlusTableInclude( this );
		}
		return( tableCPlusTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusTableMembers getTableCPlusTableMembersHandler() {
		if( tableCPlusTableMembersHandler == null ) {
			tableCPlusTableMembersHandler = new CFBamSaxLoaderTableCPlusTableMembers( this );
		}
		return( tableCPlusTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCPlusTableInterface getTableCPlusTableInterfaceHandler() {
		if( tableCPlusTableInterfaceHandler == null ) {
			tableCPlusTableInterfaceHandler = new CFBamSaxLoaderTableCPlusTableInterface( this );
		}
		return( tableCPlusTableInterfaceHandler );
	}
	protected CFBamSaxLoaderTableCPlusTableImplementation getTableCPlusTableImplementationHandler() {
		if( tableCPlusTableImplementationHandler == null ) {
			tableCPlusTableImplementationHandler = new CFBamSaxLoaderTableCPlusTableImplementation( this );
		}
		return( tableCPlusTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCPlusTableObjInclude getTableCPlusTableObjIncludeHandler() {
		if( tableCPlusTableObjIncludeHandler == null ) {
			tableCPlusTableObjIncludeHandler = new CFBamSaxLoaderTableCPlusTableObjInclude( this );
		}
		return( tableCPlusTableObjIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusTableObjMembers getTableCPlusTableObjMembersHandler() {
		if( tableCPlusTableObjMembersHandler == null ) {
			tableCPlusTableObjMembersHandler = new CFBamSaxLoaderTableCPlusTableObjMembers( this );
		}
		return( tableCPlusTableObjMembersHandler );
	}
	protected CFBamSaxLoaderTableCPlusTableObjInterface getTableCPlusTableObjInterfaceHandler() {
		if( tableCPlusTableObjInterfaceHandler == null ) {
			tableCPlusTableObjInterfaceHandler = new CFBamSaxLoaderTableCPlusTableObjInterface( this );
		}
		return( tableCPlusTableObjInterfaceHandler );
	}
	protected CFBamSaxLoaderTableCPlusTableObjImplementation getTableCPlusTableObjImplementationHandler() {
		if( tableCPlusTableObjImplementationHandler == null ) {
			tableCPlusTableObjImplementationHandler = new CFBamSaxLoaderTableCPlusTableObjImplementation( this );
		}
		return( tableCPlusTableObjImplementationHandler );
	}
	protected CFBamSaxLoaderTableCPlusDb2LUWTableInclude getTableCPlusDb2LUWTableIncludeHandler() {
		if( tableCPlusDb2LUWTableIncludeHandler == null ) {
			tableCPlusDb2LUWTableIncludeHandler = new CFBamSaxLoaderTableCPlusDb2LUWTableInclude( this );
		}
		return( tableCPlusDb2LUWTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusDb2LUWTableMembers getTableCPlusDb2LUWTableMembersHandler() {
		if( tableCPlusDb2LUWTableMembersHandler == null ) {
			tableCPlusDb2LUWTableMembersHandler = new CFBamSaxLoaderTableCPlusDb2LUWTableMembers( this );
		}
		return( tableCPlusDb2LUWTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCPlusDb2LUWTableImplementation getTableCPlusDb2LUWTableImplementationHandler() {
		if( tableCPlusDb2LUWTableImplementationHandler == null ) {
			tableCPlusDb2LUWTableImplementationHandler = new CFBamSaxLoaderTableCPlusDb2LUWTableImplementation( this );
		}
		return( tableCPlusDb2LUWTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCPlusMSSqlTableInclude getTableCPlusMSSqlTableIncludeHandler() {
		if( tableCPlusMSSqlTableIncludeHandler == null ) {
			tableCPlusMSSqlTableIncludeHandler = new CFBamSaxLoaderTableCPlusMSSqlTableInclude( this );
		}
		return( tableCPlusMSSqlTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusMSSqlTableMembers getTableCPlusMSSqlTableMembersHandler() {
		if( tableCPlusMSSqlTableMembersHandler == null ) {
			tableCPlusMSSqlTableMembersHandler = new CFBamSaxLoaderTableCPlusMSSqlTableMembers( this );
		}
		return( tableCPlusMSSqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCPlusMSSqlTableImplementation getTableCPlusMSSqlTableImplementationHandler() {
		if( tableCPlusMSSqlTableImplementationHandler == null ) {
			tableCPlusMSSqlTableImplementationHandler = new CFBamSaxLoaderTableCPlusMSSqlTableImplementation( this );
		}
		return( tableCPlusMSSqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCPlusMySqlTableInclude getTableCPlusMySqlTableIncludeHandler() {
		if( tableCPlusMySqlTableIncludeHandler == null ) {
			tableCPlusMySqlTableIncludeHandler = new CFBamSaxLoaderTableCPlusMySqlTableInclude( this );
		}
		return( tableCPlusMySqlTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusMySqlTableMembers getTableCPlusMySqlTableMembersHandler() {
		if( tableCPlusMySqlTableMembersHandler == null ) {
			tableCPlusMySqlTableMembersHandler = new CFBamSaxLoaderTableCPlusMySqlTableMembers( this );
		}
		return( tableCPlusMySqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCPlusMySqlTableImplementation getTableCPlusMySqlTableImplementationHandler() {
		if( tableCPlusMySqlTableImplementationHandler == null ) {
			tableCPlusMySqlTableImplementationHandler = new CFBamSaxLoaderTableCPlusMySqlTableImplementation( this );
		}
		return( tableCPlusMySqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCPlusOracleTableInclude getTableCPlusOracleTableIncludeHandler() {
		if( tableCPlusOracleTableIncludeHandler == null ) {
			tableCPlusOracleTableIncludeHandler = new CFBamSaxLoaderTableCPlusOracleTableInclude( this );
		}
		return( tableCPlusOracleTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusOracleTableMembers getTableCPlusOracleTableMembersHandler() {
		if( tableCPlusOracleTableMembersHandler == null ) {
			tableCPlusOracleTableMembersHandler = new CFBamSaxLoaderTableCPlusOracleTableMembers( this );
		}
		return( tableCPlusOracleTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCPlusOracleTableImplementation getTableCPlusOracleTableImplementationHandler() {
		if( tableCPlusOracleTableImplementationHandler == null ) {
			tableCPlusOracleTableImplementationHandler = new CFBamSaxLoaderTableCPlusOracleTableImplementation( this );
		}
		return( tableCPlusOracleTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCPlusPgSqlTableMembers getTableCPlusPgSqlTableMembersHandler() {
		if( tableCPlusPgSqlTableMembersHandler == null ) {
			tableCPlusPgSqlTableMembersHandler = new CFBamSaxLoaderTableCPlusPgSqlTableMembers( this );
		}
		return( tableCPlusPgSqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCPlusPgSqlTableImplementation getTableCPlusPgSqlTableImplementationHandler() {
		if( tableCPlusPgSqlTableImplementationHandler == null ) {
			tableCPlusPgSqlTableImplementationHandler = new CFBamSaxLoaderTableCPlusPgSqlTableImplementation( this );
		}
		return( tableCPlusPgSqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCPlusRamTableInclude getTableCPlusRamTableIncludeHandler() {
		if( tableCPlusRamTableIncludeHandler == null ) {
			tableCPlusRamTableIncludeHandler = new CFBamSaxLoaderTableCPlusRamTableInclude( this );
		}
		return( tableCPlusRamTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusRamTableMembers getTableCPlusRamTableMembersHandler() {
		if( tableCPlusRamTableMembersHandler == null ) {
			tableCPlusRamTableMembersHandler = new CFBamSaxLoaderTableCPlusRamTableMembers( this );
		}
		return( tableCPlusRamTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCPlusRamTableImplementation getTableCPlusRamTableImplementationHandler() {
		if( tableCPlusRamTableImplementationHandler == null ) {
			tableCPlusRamTableImplementationHandler = new CFBamSaxLoaderTableCPlusRamTableImplementation( this );
		}
		return( tableCPlusRamTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCPlusSaxLoaderInclude getTableCPlusSaxLoaderIncludeHandler() {
		if( tableCPlusSaxLoaderIncludeHandler == null ) {
			tableCPlusSaxLoaderIncludeHandler = new CFBamSaxLoaderTableCPlusSaxLoaderInclude( this );
		}
		return( tableCPlusSaxLoaderIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusSaxLoaderStartElement getTableCPlusSaxLoaderStartElementHandler() {
		if( tableCPlusSaxLoaderStartElementHandler == null ) {
			tableCPlusSaxLoaderStartElementHandler = new CFBamSaxLoaderTableCPlusSaxLoaderStartElement( this );
		}
		return( tableCPlusSaxLoaderStartElementHandler );
	}
	protected CFBamSaxLoaderTableCPlusSaxLoaderEndElement getTableCPlusSaxLoaderEndElementHandler() {
		if( tableCPlusSaxLoaderEndElementHandler == null ) {
			tableCPlusSaxLoaderEndElementHandler = new CFBamSaxLoaderTableCPlusSaxLoaderEndElement( this );
		}
		return( tableCPlusSaxLoaderEndElementHandler );
	}
	protected CFBamSaxLoaderTableCPlusXMsgTableInclude getTableCPlusXMsgTableIncludeHandler() {
		if( tableCPlusXMsgTableIncludeHandler == null ) {
			tableCPlusXMsgTableIncludeHandler = new CFBamSaxLoaderTableCPlusXMsgTableInclude( this );
		}
		return( tableCPlusXMsgTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusXMsgTableFormatters getTableCPlusXMsgTableFormattersHandler() {
		if( tableCPlusXMsgTableFormattersHandler == null ) {
			tableCPlusXMsgTableFormattersHandler = new CFBamSaxLoaderTableCPlusXMsgTableFormatters( this );
		}
		return( tableCPlusXMsgTableFormattersHandler );
	}
	protected CFBamSaxLoaderTableCPlusXMsgRqstTableInclude getTableCPlusXMsgRqstTableIncludeHandler() {
		if( tableCPlusXMsgRqstTableIncludeHandler == null ) {
			tableCPlusXMsgRqstTableIncludeHandler = new CFBamSaxLoaderTableCPlusXMsgRqstTableInclude( this );
		}
		return( tableCPlusXMsgRqstTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusXMsgRspnTableInclude getTableCPlusXMsgRspnTableIncludeHandler() {
		if( tableCPlusXMsgRspnTableIncludeHandler == null ) {
			tableCPlusXMsgRspnTableIncludeHandler = new CFBamSaxLoaderTableCPlusXMsgRspnTableInclude( this );
		}
		return( tableCPlusXMsgRspnTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusXMsgClientTableInclude getTableCPlusXMsgClientTableIncludeHandler() {
		if( tableCPlusXMsgClientTableIncludeHandler == null ) {
			tableCPlusXMsgClientTableIncludeHandler = new CFBamSaxLoaderTableCPlusXMsgClientTableInclude( this );
		}
		return( tableCPlusXMsgClientTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableCPlusXMsgRqstTableBody getTableCPlusXMsgRqstTableBodyHandler() {
		if( tableCPlusXMsgRqstTableBodyHandler == null ) {
			tableCPlusXMsgRqstTableBodyHandler = new CFBamSaxLoaderTableCPlusXMsgRqstTableBody( this );
		}
		return( tableCPlusXMsgRqstTableBodyHandler );
	}
	protected CFBamSaxLoaderTableCPlusXMsgRspnTableBody getTableCPlusXMsgRspnTableBodyHandler() {
		if( tableCPlusXMsgRspnTableBodyHandler == null ) {
			tableCPlusXMsgRspnTableBodyHandler = new CFBamSaxLoaderTableCPlusXMsgRspnTableBody( this );
		}
		return( tableCPlusXMsgRspnTableBodyHandler );
	}
	protected CFBamSaxLoaderTableCPlusXMsgClientTableBody getTableCPlusXMsgClientTableBodyHandler() {
		if( tableCPlusXMsgClientTableBodyHandler == null ) {
			tableCPlusXMsgClientTableBodyHandler = new CFBamSaxLoaderTableCPlusXMsgClientTableBody( this );
		}
		return( tableCPlusXMsgClientTableBodyHandler );
	}
	protected CFBamSaxLoaderTableHPlusObjMembers getTableHPlusObjMembersHandler() {
		if( tableHPlusObjMembersHandler == null ) {
			tableHPlusObjMembersHandler = new CFBamSaxLoaderTableHPlusObjMembers( this );
		}
		return( tableHPlusObjMembersHandler );
	}
	protected CFBamSaxLoaderTableHPlusObjInterface getTableHPlusObjInterfaceHandler() {
		if( tableHPlusObjInterfaceHandler == null ) {
			tableHPlusObjInterfaceHandler = new CFBamSaxLoaderTableHPlusObjInterface( this );
		}
		return( tableHPlusObjInterfaceHandler );
	}
	protected CFBamSaxLoaderTableHPlusObjInclude getTableHPlusObjIncludeHandler() {
		if( tableHPlusObjIncludeHandler == null ) {
			tableHPlusObjIncludeHandler = new CFBamSaxLoaderTableHPlusObjInclude( this );
		}
		return( tableHPlusObjIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusObjImplemntation getTableHPlusObjImplemntationHandler() {
		if( tableHPlusObjImplemntationHandler == null ) {
			tableHPlusObjImplemntationHandler = new CFBamSaxLoaderTableHPlusObjImplemntation( this );
		}
		return( tableHPlusObjImplemntationHandler );
	}
	protected CFBamSaxLoaderTableHPlusEditObjMembers getTableHPlusEditObjMembersHandler() {
		if( tableHPlusEditObjMembersHandler == null ) {
			tableHPlusEditObjMembersHandler = new CFBamSaxLoaderTableHPlusEditObjMembers( this );
		}
		return( tableHPlusEditObjMembersHandler );
	}
	protected CFBamSaxLoaderTableHPlusEditObjInterface getTableHPlusEditObjInterfaceHandler() {
		if( tableHPlusEditObjInterfaceHandler == null ) {
			tableHPlusEditObjInterfaceHandler = new CFBamSaxLoaderTableHPlusEditObjInterface( this );
		}
		return( tableHPlusEditObjInterfaceHandler );
	}
	protected CFBamSaxLoaderTableHPlusEditObjInclude getTableHPlusEditObjIncludeHandler() {
		if( tableHPlusEditObjIncludeHandler == null ) {
			tableHPlusEditObjIncludeHandler = new CFBamSaxLoaderTableHPlusEditObjInclude( this );
		}
		return( tableHPlusEditObjIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusEditObjImplementation getTableHPlusEditObjImplementationHandler() {
		if( tableHPlusEditObjImplementationHandler == null ) {
			tableHPlusEditObjImplementationHandler = new CFBamSaxLoaderTableHPlusEditObjImplementation( this );
		}
		return( tableHPlusEditObjImplementationHandler );
	}
	protected CFBamSaxLoaderTableHPlusTableInclude getTableHPlusTableIncludeHandler() {
		if( tableHPlusTableIncludeHandler == null ) {
			tableHPlusTableIncludeHandler = new CFBamSaxLoaderTableHPlusTableInclude( this );
		}
		return( tableHPlusTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusTableMembers getTableHPlusTableMembersHandler() {
		if( tableHPlusTableMembersHandler == null ) {
			tableHPlusTableMembersHandler = new CFBamSaxLoaderTableHPlusTableMembers( this );
		}
		return( tableHPlusTableMembersHandler );
	}
	protected CFBamSaxLoaderTableHPlusTableInterface getTableHPlusTableInterfaceHandler() {
		if( tableHPlusTableInterfaceHandler == null ) {
			tableHPlusTableInterfaceHandler = new CFBamSaxLoaderTableHPlusTableInterface( this );
		}
		return( tableHPlusTableInterfaceHandler );
	}
	protected CFBamSaxLoaderTableHPlusTableImplementation getTableHPlusTableImplementationHandler() {
		if( tableHPlusTableImplementationHandler == null ) {
			tableHPlusTableImplementationHandler = new CFBamSaxLoaderTableHPlusTableImplementation( this );
		}
		return( tableHPlusTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableHPlusTableObjInclude getTableHPlusTableObjIncludeHandler() {
		if( tableHPlusTableObjIncludeHandler == null ) {
			tableHPlusTableObjIncludeHandler = new CFBamSaxLoaderTableHPlusTableObjInclude( this );
		}
		return( tableHPlusTableObjIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusTableObjMembers getTableHPlusTableObjMembersHandler() {
		if( tableHPlusTableObjMembersHandler == null ) {
			tableHPlusTableObjMembersHandler = new CFBamSaxLoaderTableHPlusTableObjMembers( this );
		}
		return( tableHPlusTableObjMembersHandler );
	}
	protected CFBamSaxLoaderTableHPlusTableObjInterface getTableHPlusTableObjInterfaceHandler() {
		if( tableHPlusTableObjInterfaceHandler == null ) {
			tableHPlusTableObjInterfaceHandler = new CFBamSaxLoaderTableHPlusTableObjInterface( this );
		}
		return( tableHPlusTableObjInterfaceHandler );
	}
	protected CFBamSaxLoaderTableHPlusTableObjImplementation getTableHPlusTableObjImplementationHandler() {
		if( tableHPlusTableObjImplementationHandler == null ) {
			tableHPlusTableObjImplementationHandler = new CFBamSaxLoaderTableHPlusTableObjImplementation( this );
		}
		return( tableHPlusTableObjImplementationHandler );
	}
	protected CFBamSaxLoaderTableHPlusDb2LUWTableInclude getTableHPlusDb2LUWTableIncludeHandler() {
		if( tableHPlusDb2LUWTableIncludeHandler == null ) {
			tableHPlusDb2LUWTableIncludeHandler = new CFBamSaxLoaderTableHPlusDb2LUWTableInclude( this );
		}
		return( tableHPlusDb2LUWTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusDb2LUWTableMembers getTableHPlusDb2LUWTableMembersHandler() {
		if( tableHPlusDb2LUWTableMembersHandler == null ) {
			tableHPlusDb2LUWTableMembersHandler = new CFBamSaxLoaderTableHPlusDb2LUWTableMembers( this );
		}
		return( tableHPlusDb2LUWTableMembersHandler );
	}
	protected CFBamSaxLoaderTableHPlusDb2LUWTableImplementation getTableHPlusDb2LUWTableImplementationHandler() {
		if( tableHPlusDb2LUWTableImplementationHandler == null ) {
			tableHPlusDb2LUWTableImplementationHandler = new CFBamSaxLoaderTableHPlusDb2LUWTableImplementation( this );
		}
		return( tableHPlusDb2LUWTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableHPlusMSSqlTableInclude getTableHPlusMSSqlTableIncludeHandler() {
		if( tableHPlusMSSqlTableIncludeHandler == null ) {
			tableHPlusMSSqlTableIncludeHandler = new CFBamSaxLoaderTableHPlusMSSqlTableInclude( this );
		}
		return( tableHPlusMSSqlTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusMSSqlTableMembers getTableHPlusMSSqlTableMembersHandler() {
		if( tableHPlusMSSqlTableMembersHandler == null ) {
			tableHPlusMSSqlTableMembersHandler = new CFBamSaxLoaderTableHPlusMSSqlTableMembers( this );
		}
		return( tableHPlusMSSqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableHPlusMSSqlTableImplementation getTableHPlusMSSqlTableImplementationHandler() {
		if( tableHPlusMSSqlTableImplementationHandler == null ) {
			tableHPlusMSSqlTableImplementationHandler = new CFBamSaxLoaderTableHPlusMSSqlTableImplementation( this );
		}
		return( tableHPlusMSSqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableHPlusMySqlTableInclude getTableHPlusMySqlTableIncludeHandler() {
		if( tableHPlusMySqlTableIncludeHandler == null ) {
			tableHPlusMySqlTableIncludeHandler = new CFBamSaxLoaderTableHPlusMySqlTableInclude( this );
		}
		return( tableHPlusMySqlTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusMySqlTableMembers getTableHPlusMySqlTableMembersHandler() {
		if( tableHPlusMySqlTableMembersHandler == null ) {
			tableHPlusMySqlTableMembersHandler = new CFBamSaxLoaderTableHPlusMySqlTableMembers( this );
		}
		return( tableHPlusMySqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableHPlusMySqlTableImplementation getTableHPlusMySqlTableImplementationHandler() {
		if( tableHPlusMySqlTableImplementationHandler == null ) {
			tableHPlusMySqlTableImplementationHandler = new CFBamSaxLoaderTableHPlusMySqlTableImplementation( this );
		}
		return( tableHPlusMySqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableHPlusOracleTableInclude getTableHPlusOracleTableIncludeHandler() {
		if( tableHPlusOracleTableIncludeHandler == null ) {
			tableHPlusOracleTableIncludeHandler = new CFBamSaxLoaderTableHPlusOracleTableInclude( this );
		}
		return( tableHPlusOracleTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusOracleTableMembers getTableHPlusOracleTableMembersHandler() {
		if( tableHPlusOracleTableMembersHandler == null ) {
			tableHPlusOracleTableMembersHandler = new CFBamSaxLoaderTableHPlusOracleTableMembers( this );
		}
		return( tableHPlusOracleTableMembersHandler );
	}
	protected CFBamSaxLoaderTableHPlusOracleTableImplementation getTableHPlusOracleTableImplementationHandler() {
		if( tableHPlusOracleTableImplementationHandler == null ) {
			tableHPlusOracleTableImplementationHandler = new CFBamSaxLoaderTableHPlusOracleTableImplementation( this );
		}
		return( tableHPlusOracleTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableHPlusPgSqlTableInclude getTableHPlusPgSqlTableIncludeHandler() {
		if( tableHPlusPgSqlTableIncludeHandler == null ) {
			tableHPlusPgSqlTableIncludeHandler = new CFBamSaxLoaderTableHPlusPgSqlTableInclude( this );
		}
		return( tableHPlusPgSqlTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusPgSqlTableMembers getTableHPlusPgSqlTableMembersHandler() {
		if( tableHPlusPgSqlTableMembersHandler == null ) {
			tableHPlusPgSqlTableMembersHandler = new CFBamSaxLoaderTableHPlusPgSqlTableMembers( this );
		}
		return( tableHPlusPgSqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableHPlusPgSqlTableImplementation getTableHPlusPgSqlTableImplementationHandler() {
		if( tableHPlusPgSqlTableImplementationHandler == null ) {
			tableHPlusPgSqlTableImplementationHandler = new CFBamSaxLoaderTableHPlusPgSqlTableImplementation( this );
		}
		return( tableHPlusPgSqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableHPlusRamTableInclude getTableHPlusRamTableIncludeHandler() {
		if( tableHPlusRamTableIncludeHandler == null ) {
			tableHPlusRamTableIncludeHandler = new CFBamSaxLoaderTableHPlusRamTableInclude( this );
		}
		return( tableHPlusRamTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusRamTableMembers getTableHPlusRamTableMembersHandler() {
		if( tableHPlusRamTableMembersHandler == null ) {
			tableHPlusRamTableMembersHandler = new CFBamSaxLoaderTableHPlusRamTableMembers( this );
		}
		return( tableHPlusRamTableMembersHandler );
	}
	protected CFBamSaxLoaderTableHPlusRamTableImplementation getTableHPlusRamTableImplementationHandler() {
		if( tableHPlusRamTableImplementationHandler == null ) {
			tableHPlusRamTableImplementationHandler = new CFBamSaxLoaderTableHPlusRamTableImplementation( this );
		}
		return( tableHPlusRamTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableHPlusSaxLoaderInclude getTableHPlusSaxLoaderIncludeHandler() {
		if( tableHPlusSaxLoaderIncludeHandler == null ) {
			tableHPlusSaxLoaderIncludeHandler = new CFBamSaxLoaderTableHPlusSaxLoaderInclude( this );
		}
		return( tableHPlusSaxLoaderIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusSaxLoaderStartElement getTableHPlusSaxLoaderStartElementHandler() {
		if( tableHPlusSaxLoaderStartElementHandler == null ) {
			tableHPlusSaxLoaderStartElementHandler = new CFBamSaxLoaderTableHPlusSaxLoaderStartElement( this );
		}
		return( tableHPlusSaxLoaderStartElementHandler );
	}
	protected CFBamSaxLoaderTableHPlusSaxLoaderEndElement getTableHPlusSaxLoaderEndElementHandler() {
		if( tableHPlusSaxLoaderEndElementHandler == null ) {
			tableHPlusSaxLoaderEndElementHandler = new CFBamSaxLoaderTableHPlusSaxLoaderEndElement( this );
		}
		return( tableHPlusSaxLoaderEndElementHandler );
	}
	protected CFBamSaxLoaderTableHPlusXMsgTableInclude getTableHPlusXMsgTableIncludeHandler() {
		if( tableHPlusXMsgTableIncludeHandler == null ) {
			tableHPlusXMsgTableIncludeHandler = new CFBamSaxLoaderTableHPlusXMsgTableInclude( this );
		}
		return( tableHPlusXMsgTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusXMsgTableFormatters getTableHPlusXMsgTableFormattersHandler() {
		if( tableHPlusXMsgTableFormattersHandler == null ) {
			tableHPlusXMsgTableFormattersHandler = new CFBamSaxLoaderTableHPlusXMsgTableFormatters( this );
		}
		return( tableHPlusXMsgTableFormattersHandler );
	}
	protected CFBamSaxLoaderTableHPlusXMsgRqstTableInclude getTableHPlusXMsgRqstTableIncludeHandler() {
		if( tableHPlusXMsgRqstTableIncludeHandler == null ) {
			tableHPlusXMsgRqstTableIncludeHandler = new CFBamSaxLoaderTableHPlusXMsgRqstTableInclude( this );
		}
		return( tableHPlusXMsgRqstTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusXMsgRspnTableInclude getTableHPlusXMsgRspnTableIncludeHandler() {
		if( tableHPlusXMsgRspnTableIncludeHandler == null ) {
			tableHPlusXMsgRspnTableIncludeHandler = new CFBamSaxLoaderTableHPlusXMsgRspnTableInclude( this );
		}
		return( tableHPlusXMsgRspnTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusXMsgClientTableInclude getTableHPlusXMsgClientTableIncludeHandler() {
		if( tableHPlusXMsgClientTableIncludeHandler == null ) {
			tableHPlusXMsgClientTableIncludeHandler = new CFBamSaxLoaderTableHPlusXMsgClientTableInclude( this );
		}
		return( tableHPlusXMsgClientTableIncludeHandler );
	}
	protected CFBamSaxLoaderTableHPlusXMsgRqstTableBody getTableHPlusXMsgRqstTableBodyHandler() {
		if( tableHPlusXMsgRqstTableBodyHandler == null ) {
			tableHPlusXMsgRqstTableBodyHandler = new CFBamSaxLoaderTableHPlusXMsgRqstTableBody( this );
		}
		return( tableHPlusXMsgRqstTableBodyHandler );
	}
	protected CFBamSaxLoaderTableHPlusXMsgRspnTableBody getTableHPlusXMsgRspnTableBodyHandler() {
		if( tableHPlusXMsgRspnTableBodyHandler == null ) {
			tableHPlusXMsgRspnTableBodyHandler = new CFBamSaxLoaderTableHPlusXMsgRspnTableBody( this );
		}
		return( tableHPlusXMsgRspnTableBodyHandler );
	}
	protected CFBamSaxLoaderTableHPlusXMsgClientTableBody getTableHPlusXMsgClientTableBodyHandler() {
		if( tableHPlusXMsgClientTableBodyHandler == null ) {
			tableHPlusXMsgClientTableBodyHandler = new CFBamSaxLoaderTableHPlusXMsgClientTableBody( this );
		}
		return( tableHPlusXMsgClientTableBodyHandler );
	}
	protected CFBamSaxLoaderTableCSharpObjMembers getTableCSharpObjMembersHandler() {
		if( tableCSharpObjMembersHandler == null ) {
			tableCSharpObjMembersHandler = new CFBamSaxLoaderTableCSharpObjMembers( this );
		}
		return( tableCSharpObjMembersHandler );
	}
	protected CFBamSaxLoaderTableCSharpObjInterface getTableCSharpObjInterfaceHandler() {
		if( tableCSharpObjInterfaceHandler == null ) {
			tableCSharpObjInterfaceHandler = new CFBamSaxLoaderTableCSharpObjInterface( this );
		}
		return( tableCSharpObjInterfaceHandler );
	}
	protected CFBamSaxLoaderTableCSharpObjUsing getTableCSharpObjUsingHandler() {
		if( tableCSharpObjUsingHandler == null ) {
			tableCSharpObjUsingHandler = new CFBamSaxLoaderTableCSharpObjUsing( this );
		}
		return( tableCSharpObjUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpObjImplementation getTableCSharpObjImplementationHandler() {
		if( tableCSharpObjImplementationHandler == null ) {
			tableCSharpObjImplementationHandler = new CFBamSaxLoaderTableCSharpObjImplementation( this );
		}
		return( tableCSharpObjImplementationHandler );
	}
	protected CFBamSaxLoaderTableCSharpEditObjMembers getTableCSharpEditObjMembersHandler() {
		if( tableCSharpEditObjMembersHandler == null ) {
			tableCSharpEditObjMembersHandler = new CFBamSaxLoaderTableCSharpEditObjMembers( this );
		}
		return( tableCSharpEditObjMembersHandler );
	}
	protected CFBamSaxLoaderTableCSharpEditObjInterface getTableCSharpEditObjInterfaceHandler() {
		if( tableCSharpEditObjInterfaceHandler == null ) {
			tableCSharpEditObjInterfaceHandler = new CFBamSaxLoaderTableCSharpEditObjInterface( this );
		}
		return( tableCSharpEditObjInterfaceHandler );
	}
	protected CFBamSaxLoaderTableCSharpEditObjUsing getTableCSharpEditObjUsingHandler() {
		if( tableCSharpEditObjUsingHandler == null ) {
			tableCSharpEditObjUsingHandler = new CFBamSaxLoaderTableCSharpEditObjUsing( this );
		}
		return( tableCSharpEditObjUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpEditObjImplementation getTableCSharpEditObjImplementationHandler() {
		if( tableCSharpEditObjImplementationHandler == null ) {
			tableCSharpEditObjImplementationHandler = new CFBamSaxLoaderTableCSharpEditObjImplementation( this );
		}
		return( tableCSharpEditObjImplementationHandler );
	}
	protected CFBamSaxLoaderTableCSharpTableUsing getTableCSharpTableUsingHandler() {
		if( tableCSharpTableUsingHandler == null ) {
			tableCSharpTableUsingHandler = new CFBamSaxLoaderTableCSharpTableUsing( this );
		}
		return( tableCSharpTableUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpTableMembers getTableCSharpTableMembersHandler() {
		if( tableCSharpTableMembersHandler == null ) {
			tableCSharpTableMembersHandler = new CFBamSaxLoaderTableCSharpTableMembers( this );
		}
		return( tableCSharpTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCSharpTableInterface getTableCSharpTableInterfaceHandler() {
		if( tableCSharpTableInterfaceHandler == null ) {
			tableCSharpTableInterfaceHandler = new CFBamSaxLoaderTableCSharpTableInterface( this );
		}
		return( tableCSharpTableInterfaceHandler );
	}
	protected CFBamSaxLoaderTableCSharpTableImplementation getTableCSharpTableImplementationHandler() {
		if( tableCSharpTableImplementationHandler == null ) {
			tableCSharpTableImplementationHandler = new CFBamSaxLoaderTableCSharpTableImplementation( this );
		}
		return( tableCSharpTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCSharpTableObjUsing getTableCSharpTableObjUsingHandler() {
		if( tableCSharpTableObjUsingHandler == null ) {
			tableCSharpTableObjUsingHandler = new CFBamSaxLoaderTableCSharpTableObjUsing( this );
		}
		return( tableCSharpTableObjUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpTableObjMembers getTableCSharpTableObjMembersHandler() {
		if( tableCSharpTableObjMembersHandler == null ) {
			tableCSharpTableObjMembersHandler = new CFBamSaxLoaderTableCSharpTableObjMembers( this );
		}
		return( tableCSharpTableObjMembersHandler );
	}
	protected CFBamSaxLoaderTableCSharpTableObjInterface getTableCSharpTableObjInterfaceHandler() {
		if( tableCSharpTableObjInterfaceHandler == null ) {
			tableCSharpTableObjInterfaceHandler = new CFBamSaxLoaderTableCSharpTableObjInterface( this );
		}
		return( tableCSharpTableObjInterfaceHandler );
	}
	protected CFBamSaxLoaderTableCSharpTableObjImplementation getTableCSharpTableObjImplementationHandler() {
		if( tableCSharpTableObjImplementationHandler == null ) {
			tableCSharpTableObjImplementationHandler = new CFBamSaxLoaderTableCSharpTableObjImplementation( this );
		}
		return( tableCSharpTableObjImplementationHandler );
	}
	protected CFBamSaxLoaderTableCSharpDb2LUWTableUsing getTableCSharpDb2LUWTableUsingHandler() {
		if( tableCSharpDb2LUWTableUsingHandler == null ) {
			tableCSharpDb2LUWTableUsingHandler = new CFBamSaxLoaderTableCSharpDb2LUWTableUsing( this );
		}
		return( tableCSharpDb2LUWTableUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpDb2LUWTableMembers getTableCSharpDb2LUWTableMembersHandler() {
		if( tableCSharpDb2LUWTableMembersHandler == null ) {
			tableCSharpDb2LUWTableMembersHandler = new CFBamSaxLoaderTableCSharpDb2LUWTableMembers( this );
		}
		return( tableCSharpDb2LUWTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCSharpDb2LUWTableImplementation getTableCSharpDb2LUWTableImplementationHandler() {
		if( tableCSharpDb2LUWTableImplementationHandler == null ) {
			tableCSharpDb2LUWTableImplementationHandler = new CFBamSaxLoaderTableCSharpDb2LUWTableImplementation( this );
		}
		return( tableCSharpDb2LUWTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCSharpMSSqlTableUsing getTableCSharpMSSqlTableUsingHandler() {
		if( tableCSharpMSSqlTableUsingHandler == null ) {
			tableCSharpMSSqlTableUsingHandler = new CFBamSaxLoaderTableCSharpMSSqlTableUsing( this );
		}
		return( tableCSharpMSSqlTableUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpMSSqlTableMembers getTableCSharpMSSqlTableMembersHandler() {
		if( tableCSharpMSSqlTableMembersHandler == null ) {
			tableCSharpMSSqlTableMembersHandler = new CFBamSaxLoaderTableCSharpMSSqlTableMembers( this );
		}
		return( tableCSharpMSSqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCSharpMSSqlTableImplementation getTableCSharpMSSqlTableImplementationHandler() {
		if( tableCSharpMSSqlTableImplementationHandler == null ) {
			tableCSharpMSSqlTableImplementationHandler = new CFBamSaxLoaderTableCSharpMSSqlTableImplementation( this );
		}
		return( tableCSharpMSSqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCSharpMySqlTableUsing getTableCSharpMySqlTableUsingHandler() {
		if( tableCSharpMySqlTableUsingHandler == null ) {
			tableCSharpMySqlTableUsingHandler = new CFBamSaxLoaderTableCSharpMySqlTableUsing( this );
		}
		return( tableCSharpMySqlTableUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpMySqlTableMembers getTableCSharpMySqlTableMembersHandler() {
		if( tableCSharpMySqlTableMembersHandler == null ) {
			tableCSharpMySqlTableMembersHandler = new CFBamSaxLoaderTableCSharpMySqlTableMembers( this );
		}
		return( tableCSharpMySqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCSharpMySqlTableImplementation getTableCSharpMySqlTableImplementationHandler() {
		if( tableCSharpMySqlTableImplementationHandler == null ) {
			tableCSharpMySqlTableImplementationHandler = new CFBamSaxLoaderTableCSharpMySqlTableImplementation( this );
		}
		return( tableCSharpMySqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCSharpOracleTableUsing getTableCSharpOracleTableUsingHandler() {
		if( tableCSharpOracleTableUsingHandler == null ) {
			tableCSharpOracleTableUsingHandler = new CFBamSaxLoaderTableCSharpOracleTableUsing( this );
		}
		return( tableCSharpOracleTableUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpOracleTableMembers getTableCSharpOracleTableMembersHandler() {
		if( tableCSharpOracleTableMembersHandler == null ) {
			tableCSharpOracleTableMembersHandler = new CFBamSaxLoaderTableCSharpOracleTableMembers( this );
		}
		return( tableCSharpOracleTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCSharpOracleTableImplementation getTableCSharpOracleTableImplementationHandler() {
		if( tableCSharpOracleTableImplementationHandler == null ) {
			tableCSharpOracleTableImplementationHandler = new CFBamSaxLoaderTableCSharpOracleTableImplementation( this );
		}
		return( tableCSharpOracleTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCSharpPgSqlTableUsing getTableCSharpPgSqlTableUsingHandler() {
		if( tableCSharpPgSqlTableUsingHandler == null ) {
			tableCSharpPgSqlTableUsingHandler = new CFBamSaxLoaderTableCSharpPgSqlTableUsing( this );
		}
		return( tableCSharpPgSqlTableUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpPgSqlTableMembers getTableCSharpPgSqlTableMembersHandler() {
		if( tableCSharpPgSqlTableMembersHandler == null ) {
			tableCSharpPgSqlTableMembersHandler = new CFBamSaxLoaderTableCSharpPgSqlTableMembers( this );
		}
		return( tableCSharpPgSqlTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCSharpPgSqlTableImplementation getTableCSharpPgSqlTableImplementationHandler() {
		if( tableCSharpPgSqlTableImplementationHandler == null ) {
			tableCSharpPgSqlTableImplementationHandler = new CFBamSaxLoaderTableCSharpPgSqlTableImplementation( this );
		}
		return( tableCSharpPgSqlTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCSharpRamTableUsing getTableCSharpRamTableUsingHandler() {
		if( tableCSharpRamTableUsingHandler == null ) {
			tableCSharpRamTableUsingHandler = new CFBamSaxLoaderTableCSharpRamTableUsing( this );
		}
		return( tableCSharpRamTableUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpRamTableMembers getTableCSharpRamTableMembersHandler() {
		if( tableCSharpRamTableMembersHandler == null ) {
			tableCSharpRamTableMembersHandler = new CFBamSaxLoaderTableCSharpRamTableMembers( this );
		}
		return( tableCSharpRamTableMembersHandler );
	}
	protected CFBamSaxLoaderTableCSharpRamTableImplementation getTableCSharpRamTableImplementationHandler() {
		if( tableCSharpRamTableImplementationHandler == null ) {
			tableCSharpRamTableImplementationHandler = new CFBamSaxLoaderTableCSharpRamTableImplementation( this );
		}
		return( tableCSharpRamTableImplementationHandler );
	}
	protected CFBamSaxLoaderTableCSharpSaxLoaderUsing getTableCSharpSaxLoaderUsingHandler() {
		if( tableCSharpSaxLoaderUsingHandler == null ) {
			tableCSharpSaxLoaderUsingHandler = new CFBamSaxLoaderTableCSharpSaxLoaderUsing( this );
		}
		return( tableCSharpSaxLoaderUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpSaxLoaderStartElement getTableCSharpSaxLoaderStartElementHandler() {
		if( tableCSharpSaxLoaderStartElementHandler == null ) {
			tableCSharpSaxLoaderStartElementHandler = new CFBamSaxLoaderTableCSharpSaxLoaderStartElement( this );
		}
		return( tableCSharpSaxLoaderStartElementHandler );
	}
	protected CFBamSaxLoaderTableCSharpSaxLoaderEndElement getTableCSharpSaxLoaderEndElementHandler() {
		if( tableCSharpSaxLoaderEndElementHandler == null ) {
			tableCSharpSaxLoaderEndElementHandler = new CFBamSaxLoaderTableCSharpSaxLoaderEndElement( this );
		}
		return( tableCSharpSaxLoaderEndElementHandler );
	}
	protected CFBamSaxLoaderTableCSharpXMsgTableUsing getTableCSharpXMsgTableUsingHandler() {
		if( tableCSharpXMsgTableUsingHandler == null ) {
			tableCSharpXMsgTableUsingHandler = new CFBamSaxLoaderTableCSharpXMsgTableUsing( this );
		}
		return( tableCSharpXMsgTableUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpXMsgTableFormatters getTableCSharpXMsgTableFormattersHandler() {
		if( tableCSharpXMsgTableFormattersHandler == null ) {
			tableCSharpXMsgTableFormattersHandler = new CFBamSaxLoaderTableCSharpXMsgTableFormatters( this );
		}
		return( tableCSharpXMsgTableFormattersHandler );
	}
	protected CFBamSaxLoaderTableCSharpXMsgRqstTableUsing getTableCSharpXMsgRqstTableUsingHandler() {
		if( tableCSharpXMsgRqstTableUsingHandler == null ) {
			tableCSharpXMsgRqstTableUsingHandler = new CFBamSaxLoaderTableCSharpXMsgRqstTableUsing( this );
		}
		return( tableCSharpXMsgRqstTableUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpXMsgRspnTableUsing getTableCSharpXMsgRspnTableUsingHandler() {
		if( tableCSharpXMsgRspnTableUsingHandler == null ) {
			tableCSharpXMsgRspnTableUsingHandler = new CFBamSaxLoaderTableCSharpXMsgRspnTableUsing( this );
		}
		return( tableCSharpXMsgRspnTableUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpXMsgClientTableUsing getTableCSharpXMsgClientTableUsingHandler() {
		if( tableCSharpXMsgClientTableUsingHandler == null ) {
			tableCSharpXMsgClientTableUsingHandler = new CFBamSaxLoaderTableCSharpXMsgClientTableUsing( this );
		}
		return( tableCSharpXMsgClientTableUsingHandler );
	}
	protected CFBamSaxLoaderTableCSharpXMsgRqstTableBody getTableCSharpXMsgRqstTableBodyHandler() {
		if( tableCSharpXMsgRqstTableBodyHandler == null ) {
			tableCSharpXMsgRqstTableBodyHandler = new CFBamSaxLoaderTableCSharpXMsgRqstTableBody( this );
		}
		return( tableCSharpXMsgRqstTableBodyHandler );
	}
	protected CFBamSaxLoaderTableCSharpXMsgRspnTableBody getTableCSharpXMsgRspnTableBodyHandler() {
		if( tableCSharpXMsgRspnTableBodyHandler == null ) {
			tableCSharpXMsgRspnTableBodyHandler = new CFBamSaxLoaderTableCSharpXMsgRspnTableBody( this );
		}
		return( tableCSharpXMsgRspnTableBodyHandler );
	}
	protected CFBamSaxLoaderTableCSharpXMsgClientTableBody getTableCSharpXMsgClientTableBodyHandler() {
		if( tableCSharpXMsgClientTableBodyHandler == null ) {
			tableCSharpXMsgClientTableBodyHandler = new CFBamSaxLoaderTableCSharpXMsgClientTableBody( this );
		}
		return( tableCSharpXMsgClientTableBodyHandler );
	}
	protected CFBamSaxLoaderTableCol getTableColHandler() {
		if( tableColHandler == null ) {
			tableColHandler = new CFBamSaxLoaderTableCol( this );
		}
		return( tableColHandler );
	}
	protected CFBamSaxLoaderTenant getTenantHandler() {
		if( tenantHandler == null ) {
			tenantHandler = new CFBamSaxLoaderTenant( this );
			tenantHandler.addElementHandler( "TSecGroup", getTSecGroupHandler() );
			tenantHandler.addElementHandler( "Tld", getTldHandler() );
		}
		return( tenantHandler );
	}
	protected CFBamSaxLoaderTextCol getTextColHandler() {
		if( textColHandler == null ) {
			textColHandler = new CFBamSaxLoaderTextCol( this );
		}
		return( textColHandler );
	}
	protected CFBamSaxLoaderTextDef getTextDefHandler() {
		if( textDefHandler == null ) {
			textDefHandler = new CFBamSaxLoaderTextDef( this );
		}
		return( textDefHandler );
	}
	protected CFBamSaxLoaderTextType getTextTypeHandler() {
		if( textTypeHandler == null ) {
			textTypeHandler = new CFBamSaxLoaderTextType( this );
		}
		return( textTypeHandler );
	}
	protected CFBamSaxLoaderTimeCol getTimeColHandler() {
		if( timeColHandler == null ) {
			timeColHandler = new CFBamSaxLoaderTimeCol( this );
		}
		return( timeColHandler );
	}
	protected CFBamSaxLoaderTimeDef getTimeDefHandler() {
		if( timeDefHandler == null ) {
			timeDefHandler = new CFBamSaxLoaderTimeDef( this );
		}
		return( timeDefHandler );
	}
	protected CFBamSaxLoaderTimeType getTimeTypeHandler() {
		if( timeTypeHandler == null ) {
			timeTypeHandler = new CFBamSaxLoaderTimeType( this );
		}
		return( timeTypeHandler );
	}
	protected CFBamSaxLoaderTimestampCol getTimestampColHandler() {
		if( timestampColHandler == null ) {
			timestampColHandler = new CFBamSaxLoaderTimestampCol( this );
		}
		return( timestampColHandler );
	}
	protected CFBamSaxLoaderTimestampDef getTimestampDefHandler() {
		if( timestampDefHandler == null ) {
			timestampDefHandler = new CFBamSaxLoaderTimestampDef( this );
		}
		return( timestampDefHandler );
	}
	protected CFBamSaxLoaderTimestampType getTimestampTypeHandler() {
		if( timestampTypeHandler == null ) {
			timestampTypeHandler = new CFBamSaxLoaderTimestampType( this );
		}
		return( timestampTypeHandler );
	}
	protected CFBamSaxLoaderTld getTldHandler() {
		if( tldHandler == null ) {
			tldHandler = new CFBamSaxLoaderTld( this );
			tldHandler.addElementHandler( "TopDomain", getTopDomainHandler() );
		}
		return( tldHandler );
	}
	protected CFBamSaxLoaderTokenCol getTokenColHandler() {
		if( tokenColHandler == null ) {
			tokenColHandler = new CFBamSaxLoaderTokenCol( this );
		}
		return( tokenColHandler );
	}
	protected CFBamSaxLoaderTokenDef getTokenDefHandler() {
		if( tokenDefHandler == null ) {
			tokenDefHandler = new CFBamSaxLoaderTokenDef( this );
		}
		return( tokenDefHandler );
	}
	protected CFBamSaxLoaderTokenType getTokenTypeHandler() {
		if( tokenTypeHandler == null ) {
			tokenTypeHandler = new CFBamSaxLoaderTokenType( this );
		}
		return( tokenTypeHandler );
	}
	protected CFBamSaxLoaderTopDomain getTopDomainHandler() {
		if( topDomainHandler == null ) {
			topDomainHandler = new CFBamSaxLoaderTopDomain( this );
			topDomainHandler.addElementHandler( "License", getLicenseHandler() );
			topDomainHandler.addElementHandler( "TopProject", getTopProjectHandler() );
		}
		return( topDomainHandler );
	}
	protected CFBamSaxLoaderTopProject getTopProjectHandler() {
		if( topProjectHandler == null ) {
			topProjectHandler = new CFBamSaxLoaderTopProject( this );
			topProjectHandler.addElementHandler( "SubProject", getSubProjectHandler() );
		}
		return( topProjectHandler );
	}
	protected CFBamSaxLoaderUInt16Col getUInt16ColHandler() {
		if( uInt16ColHandler == null ) {
			uInt16ColHandler = new CFBamSaxLoaderUInt16Col( this );
		}
		return( uInt16ColHandler );
	}
	protected CFBamSaxLoaderUInt16Def getUInt16DefHandler() {
		if( uInt16DefHandler == null ) {
			uInt16DefHandler = new CFBamSaxLoaderUInt16Def( this );
		}
		return( uInt16DefHandler );
	}
	protected CFBamSaxLoaderUInt16Type getUInt16TypeHandler() {
		if( uInt16TypeHandler == null ) {
			uInt16TypeHandler = new CFBamSaxLoaderUInt16Type( this );
		}
		return( uInt16TypeHandler );
	}
	protected CFBamSaxLoaderUInt32Col getUInt32ColHandler() {
		if( uInt32ColHandler == null ) {
			uInt32ColHandler = new CFBamSaxLoaderUInt32Col( this );
		}
		return( uInt32ColHandler );
	}
	protected CFBamSaxLoaderUInt32Def getUInt32DefHandler() {
		if( uInt32DefHandler == null ) {
			uInt32DefHandler = new CFBamSaxLoaderUInt32Def( this );
		}
		return( uInt32DefHandler );
	}
	protected CFBamSaxLoaderUInt32Type getUInt32TypeHandler() {
		if( uInt32TypeHandler == null ) {
			uInt32TypeHandler = new CFBamSaxLoaderUInt32Type( this );
		}
		return( uInt32TypeHandler );
	}
	protected CFBamSaxLoaderUInt64Col getUInt64ColHandler() {
		if( uInt64ColHandler == null ) {
			uInt64ColHandler = new CFBamSaxLoaderUInt64Col( this );
		}
		return( uInt64ColHandler );
	}
	protected CFBamSaxLoaderUInt64Def getUInt64DefHandler() {
		if( uInt64DefHandler == null ) {
			uInt64DefHandler = new CFBamSaxLoaderUInt64Def( this );
		}
		return( uInt64DefHandler );
	}
	protected CFBamSaxLoaderUInt64Type getUInt64TypeHandler() {
		if( uInt64TypeHandler == null ) {
			uInt64TypeHandler = new CFBamSaxLoaderUInt64Type( this );
		}
		return( uInt64TypeHandler );
	}
	protected CFBamSaxLoaderURLProtocol getURLProtocolHandler() {
		if( uRLProtocolHandler == null ) {
			uRLProtocolHandler = new CFBamSaxLoaderURLProtocol( this );
		}
		return( uRLProtocolHandler );
	}
	protected CFBamSaxLoaderUuidCol getUuidColHandler() {
		if( uuidColHandler == null ) {
			uuidColHandler = new CFBamSaxLoaderUuidCol( this );
		}
		return( uuidColHandler );
	}
	protected CFBamSaxLoaderUuidDef getUuidDefHandler() {
		if( uuidDefHandler == null ) {
			uuidDefHandler = new CFBamSaxLoaderUuidDef( this );
		}
		return( uuidDefHandler );
	}
	protected CFBamSaxLoaderUuidGen getUuidGenHandler() {
		if( uuidGenHandler == null ) {
			uuidGenHandler = new CFBamSaxLoaderUuidGen( this );
		}
		return( uuidGenHandler );
	}
	protected CFBamSaxLoaderUuidType getUuidTypeHandler() {
		if( uuidTypeHandler == null ) {
			uuidTypeHandler = new CFBamSaxLoaderUuidType( this );
		}
		return( uuidTypeHandler );
	}
	protected CFBamSaxLoaderValue getValueHandler() {
		if( valueHandler == null ) {
			valueHandler = new CFBamSaxLoaderValue( this );
		}
		return( valueHandler );
	}
	// Root Element Handler Resolver Factory

	protected CFBamSaxRootHandler getSaxRootHandler() {
		if( saxRootHandler == null ) {
			saxRootHandler = new CFBamSaxRootHandler( this );
			saxRootHandler.addElementHandler( "CFBam", getSaxDocHandler() );
			saxRootHandler.addElementHandler( "CFSec", getSaxDocHandler() );
			saxRootHandler.addElementHandler( "CFInt", getSaxDocHandler() );
		}
		return( saxRootHandler );
	}

	// Root Element Handler

	/*
	 *	CFBamSaxRootHandler XML SAX Root Element Handler implementation
	 */
	public class CFBamSaxRootHandler
		extends CFLibXmlCoreElementHandler
	{
		public CFBamSaxRootHandler( CFBamSaxLoader saxLoader ) {
			super( saxLoader );
		}

		public void startElement(
			String		uri,
			String		localName,
			String		qName,
			Attributes	attrs )
		throws SAXException
		{
		}

		public void endElement(
			String		uri,
			String		localName,
			String		qName )
		throws SAXException
		{
		}
	}

	// Document Element Handler Resolver Factory

	protected CFBamSaxDocHandler getSaxDocHandler() {
		if( saxDocHandler == null ) {
			saxDocHandler = new CFBamSaxDocHandler( this );
			saxDocHandler.addElementHandler( "Cluster", getClusterHandler() );
			saxDocHandler.addElementHandler( "ISOCcy", getISOCcyHandler() );
			saxDocHandler.addElementHandler( "ISOCtry", getISOCtryHandler() );
			saxDocHandler.addElementHandler( "ISOLang", getISOLangHandler() );
			saxDocHandler.addElementHandler( "ISOTZone", getISOTZoneHandler() );
			saxDocHandler.addElementHandler( "MimeType", getMimeTypeHandler() );
			saxDocHandler.addElementHandler( "SecUser", getSecUserHandler() );
			saxDocHandler.addElementHandler( "ServiceType", getServiceTypeHandler() );
			saxDocHandler.addElementHandler( "URLProtocol", getURLProtocolHandler() );
		}
		return( saxDocHandler );
	}

	// Document Element Handler

	/*
	 *	CFBamSaxDocHandler XML SAX Doc Element Handler implementation
	 */
	public class CFBamSaxDocHandler
		extends CFLibXmlCoreElementHandler
	{
		public CFBamSaxDocHandler( CFBamSaxLoader saxLoader ) {
			super( saxLoader );
		}

		public void startElement(
			String		uri,
			String		localName,
			String		qName,
			Attributes	attrs )
		throws SAXException
		{
		}

		public void endElement(
			String		uri,
			String		localName,
			String		qName )
		throws SAXException
		{
		}
	}

	// Loader behaviour configuration accessors

	public LoaderBehaviourEnum getAtomLoaderBehaviour() {
		return( atomLoaderBehaviour );
	}

	public void setAtomLoaderBehaviour( LoaderBehaviourEnum value ) {
		atomLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getBlobColLoaderBehaviour() {
		return( blobColLoaderBehaviour );
	}

	public void setBlobColLoaderBehaviour( LoaderBehaviourEnum value ) {
		blobColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getBlobDefLoaderBehaviour() {
		return( blobDefLoaderBehaviour );
	}

	public void setBlobDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		blobDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getBlobTypeLoaderBehaviour() {
		return( blobTypeLoaderBehaviour );
	}

	public void setBlobTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		blobTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getBoolColLoaderBehaviour() {
		return( boolColLoaderBehaviour );
	}

	public void setBoolColLoaderBehaviour( LoaderBehaviourEnum value ) {
		boolColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getBoolDefLoaderBehaviour() {
		return( boolDefLoaderBehaviour );
	}

	public void setBoolDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		boolDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getBoolTypeLoaderBehaviour() {
		return( boolTypeLoaderBehaviour );
	}

	public void setBoolTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		boolTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getChainLoaderBehaviour() {
		return( chainLoaderBehaviour );
	}

	public void setChainLoaderBehaviour( LoaderBehaviourEnum value ) {
		chainLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getClearDepLoaderBehaviour() {
		return( clearDepLoaderBehaviour );
	}

	public void setClearDepLoaderBehaviour( LoaderBehaviourEnum value ) {
		clearDepLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getClearSubDep1LoaderBehaviour() {
		return( clearSubDep1LoaderBehaviour );
	}

	public void setClearSubDep1LoaderBehaviour( LoaderBehaviourEnum value ) {
		clearSubDep1LoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getClearSubDep2LoaderBehaviour() {
		return( clearSubDep2LoaderBehaviour );
	}

	public void setClearSubDep2LoaderBehaviour( LoaderBehaviourEnum value ) {
		clearSubDep2LoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getClearSubDep3LoaderBehaviour() {
		return( clearSubDep3LoaderBehaviour );
	}

	public void setClearSubDep3LoaderBehaviour( LoaderBehaviourEnum value ) {
		clearSubDep3LoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getClearTopDepLoaderBehaviour() {
		return( clearTopDepLoaderBehaviour );
	}

	public void setClearTopDepLoaderBehaviour( LoaderBehaviourEnum value ) {
		clearTopDepLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getClusterLoaderBehaviour() {
		return( clusterLoaderBehaviour );
	}

	public void setClusterLoaderBehaviour( LoaderBehaviourEnum value ) {
		clusterLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getDateColLoaderBehaviour() {
		return( dateColLoaderBehaviour );
	}

	public void setDateColLoaderBehaviour( LoaderBehaviourEnum value ) {
		dateColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getDateDefLoaderBehaviour() {
		return( dateDefLoaderBehaviour );
	}

	public void setDateDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		dateDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getDateTypeLoaderBehaviour() {
		return( dateTypeLoaderBehaviour );
	}

	public void setDateTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		dateTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getDelDepLoaderBehaviour() {
		return( delDepLoaderBehaviour );
	}

	public void setDelDepLoaderBehaviour( LoaderBehaviourEnum value ) {
		delDepLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getDelSubDep1LoaderBehaviour() {
		return( delSubDep1LoaderBehaviour );
	}

	public void setDelSubDep1LoaderBehaviour( LoaderBehaviourEnum value ) {
		delSubDep1LoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getDelSubDep2LoaderBehaviour() {
		return( delSubDep2LoaderBehaviour );
	}

	public void setDelSubDep2LoaderBehaviour( LoaderBehaviourEnum value ) {
		delSubDep2LoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getDelSubDep3LoaderBehaviour() {
		return( delSubDep3LoaderBehaviour );
	}

	public void setDelSubDep3LoaderBehaviour( LoaderBehaviourEnum value ) {
		delSubDep3LoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getDelTopDepLoaderBehaviour() {
		return( delTopDepLoaderBehaviour );
	}

	public void setDelTopDepLoaderBehaviour( LoaderBehaviourEnum value ) {
		delTopDepLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getDoubleColLoaderBehaviour() {
		return( doubleColLoaderBehaviour );
	}

	public void setDoubleColLoaderBehaviour( LoaderBehaviourEnum value ) {
		doubleColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getDoubleDefLoaderBehaviour() {
		return( doubleDefLoaderBehaviour );
	}

	public void setDoubleDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		doubleDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getDoubleTypeLoaderBehaviour() {
		return( doubleTypeLoaderBehaviour );
	}

	public void setDoubleTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		doubleTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getEnumDefLoaderBehaviour() {
		return( enumDefLoaderBehaviour );
	}

	public void setEnumDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		enumDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getEnumTagLoaderBehaviour() {
		return( enumTagLoaderBehaviour );
	}

	public void setEnumTagLoaderBehaviour( LoaderBehaviourEnum value ) {
		enumTagLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getEnumTypeLoaderBehaviour() {
		return( enumTypeLoaderBehaviour );
	}

	public void setEnumTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		enumTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getFloatColLoaderBehaviour() {
		return( floatColLoaderBehaviour );
	}

	public void setFloatColLoaderBehaviour( LoaderBehaviourEnum value ) {
		floatColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getFloatDefLoaderBehaviour() {
		return( floatDefLoaderBehaviour );
	}

	public void setFloatDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		floatDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getFloatTypeLoaderBehaviour() {
		return( floatTypeLoaderBehaviour );
	}

	public void setFloatTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		floatTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getHostNodeLoaderBehaviour() {
		return( hostNodeLoaderBehaviour );
	}

	public void setHostNodeLoaderBehaviour( LoaderBehaviourEnum value ) {
		hostNodeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getISOCcyLoaderBehaviour() {
		return( iSOCcyLoaderBehaviour );
	}

	public void setISOCcyLoaderBehaviour( LoaderBehaviourEnum value ) {
		iSOCcyLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getISOCtryLoaderBehaviour() {
		return( iSOCtryLoaderBehaviour );
	}

	public void setISOCtryLoaderBehaviour( LoaderBehaviourEnum value ) {
		iSOCtryLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getISOCtryCcyLoaderBehaviour() {
		return( iSOCtryCcyLoaderBehaviour );
	}

	public void setISOCtryCcyLoaderBehaviour( LoaderBehaviourEnum value ) {
		iSOCtryCcyLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getISOCtryLangLoaderBehaviour() {
		return( iSOCtryLangLoaderBehaviour );
	}

	public void setISOCtryLangLoaderBehaviour( LoaderBehaviourEnum value ) {
		iSOCtryLangLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getISOLangLoaderBehaviour() {
		return( iSOLangLoaderBehaviour );
	}

	public void setISOLangLoaderBehaviour( LoaderBehaviourEnum value ) {
		iSOLangLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getISOTZoneLoaderBehaviour() {
		return( iSOTZoneLoaderBehaviour );
	}

	public void setISOTZoneLoaderBehaviour( LoaderBehaviourEnum value ) {
		iSOTZoneLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getId16GenLoaderBehaviour() {
		return( id16GenLoaderBehaviour );
	}

	public void setId16GenLoaderBehaviour( LoaderBehaviourEnum value ) {
		id16GenLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getId32GenLoaderBehaviour() {
		return( id32GenLoaderBehaviour );
	}

	public void setId32GenLoaderBehaviour( LoaderBehaviourEnum value ) {
		id32GenLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getId64GenLoaderBehaviour() {
		return( id64GenLoaderBehaviour );
	}

	public void setId64GenLoaderBehaviour( LoaderBehaviourEnum value ) {
		id64GenLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getIndexLoaderBehaviour() {
		return( indexLoaderBehaviour );
	}

	public void setIndexLoaderBehaviour( LoaderBehaviourEnum value ) {
		indexLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getIndexColLoaderBehaviour() {
		return( indexColLoaderBehaviour );
	}

	public void setIndexColLoaderBehaviour( LoaderBehaviourEnum value ) {
		indexColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getInt16ColLoaderBehaviour() {
		return( int16ColLoaderBehaviour );
	}

	public void setInt16ColLoaderBehaviour( LoaderBehaviourEnum value ) {
		int16ColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getInt16DefLoaderBehaviour() {
		return( int16DefLoaderBehaviour );
	}

	public void setInt16DefLoaderBehaviour( LoaderBehaviourEnum value ) {
		int16DefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getInt16TypeLoaderBehaviour() {
		return( int16TypeLoaderBehaviour );
	}

	public void setInt16TypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		int16TypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getInt32ColLoaderBehaviour() {
		return( int32ColLoaderBehaviour );
	}

	public void setInt32ColLoaderBehaviour( LoaderBehaviourEnum value ) {
		int32ColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getInt32DefLoaderBehaviour() {
		return( int32DefLoaderBehaviour );
	}

	public void setInt32DefLoaderBehaviour( LoaderBehaviourEnum value ) {
		int32DefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getInt32TypeLoaderBehaviour() {
		return( int32TypeLoaderBehaviour );
	}

	public void setInt32TypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		int32TypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getInt64ColLoaderBehaviour() {
		return( int64ColLoaderBehaviour );
	}

	public void setInt64ColLoaderBehaviour( LoaderBehaviourEnum value ) {
		int64ColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getInt64DefLoaderBehaviour() {
		return( int64DefLoaderBehaviour );
	}

	public void setInt64DefLoaderBehaviour( LoaderBehaviourEnum value ) {
		int64DefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getInt64TypeLoaderBehaviour() {
		return( int64TypeLoaderBehaviour );
	}

	public void setInt64TypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		int64TypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getLicenseLoaderBehaviour() {
		return( licenseLoaderBehaviour );
	}

	public void setLicenseLoaderBehaviour( LoaderBehaviourEnum value ) {
		licenseLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getMajorVersionLoaderBehaviour() {
		return( majorVersionLoaderBehaviour );
	}

	public void setMajorVersionLoaderBehaviour( LoaderBehaviourEnum value ) {
		majorVersionLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getMimeTypeLoaderBehaviour() {
		return( mimeTypeLoaderBehaviour );
	}

	public void setMimeTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		mimeTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getMinorVersionLoaderBehaviour() {
		return( minorVersionLoaderBehaviour );
	}

	public void setMinorVersionLoaderBehaviour( LoaderBehaviourEnum value ) {
		minorVersionLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getNmTokenColLoaderBehaviour() {
		return( nmTokenColLoaderBehaviour );
	}

	public void setNmTokenColLoaderBehaviour( LoaderBehaviourEnum value ) {
		nmTokenColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getNmTokenDefLoaderBehaviour() {
		return( nmTokenDefLoaderBehaviour );
	}

	public void setNmTokenDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		nmTokenDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getNmTokenTypeLoaderBehaviour() {
		return( nmTokenTypeLoaderBehaviour );
	}

	public void setNmTokenTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		nmTokenTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getNmTokensColLoaderBehaviour() {
		return( nmTokensColLoaderBehaviour );
	}

	public void setNmTokensColLoaderBehaviour( LoaderBehaviourEnum value ) {
		nmTokensColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getNmTokensDefLoaderBehaviour() {
		return( nmTokensDefLoaderBehaviour );
	}

	public void setNmTokensDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		nmTokensDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getNmTokensTypeLoaderBehaviour() {
		return( nmTokensTypeLoaderBehaviour );
	}

	public void setNmTokensTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		nmTokensTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getNumberColLoaderBehaviour() {
		return( numberColLoaderBehaviour );
	}

	public void setNumberColLoaderBehaviour( LoaderBehaviourEnum value ) {
		numberColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getNumberDefLoaderBehaviour() {
		return( numberDefLoaderBehaviour );
	}

	public void setNumberDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		numberDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getNumberTypeLoaderBehaviour() {
		return( numberTypeLoaderBehaviour );
	}

	public void setNumberTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		numberTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getParamLoaderBehaviour() {
		return( paramLoaderBehaviour );
	}

	public void setParamLoaderBehaviour( LoaderBehaviourEnum value ) {
		paramLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getPopDepLoaderBehaviour() {
		return( popDepLoaderBehaviour );
	}

	public void setPopDepLoaderBehaviour( LoaderBehaviourEnum value ) {
		popDepLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getPopSubDep1LoaderBehaviour() {
		return( popSubDep1LoaderBehaviour );
	}

	public void setPopSubDep1LoaderBehaviour( LoaderBehaviourEnum value ) {
		popSubDep1LoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getPopSubDep2LoaderBehaviour() {
		return( popSubDep2LoaderBehaviour );
	}

	public void setPopSubDep2LoaderBehaviour( LoaderBehaviourEnum value ) {
		popSubDep2LoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getPopSubDep3LoaderBehaviour() {
		return( popSubDep3LoaderBehaviour );
	}

	public void setPopSubDep3LoaderBehaviour( LoaderBehaviourEnum value ) {
		popSubDep3LoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getPopTopDepLoaderBehaviour() {
		return( popTopDepLoaderBehaviour );
	}

	public void setPopTopDepLoaderBehaviour( LoaderBehaviourEnum value ) {
		popTopDepLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getRelationLoaderBehaviour() {
		return( relationLoaderBehaviour );
	}

	public void setRelationLoaderBehaviour( LoaderBehaviourEnum value ) {
		relationLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getRelationColLoaderBehaviour() {
		return( relationColLoaderBehaviour );
	}

	public void setRelationColLoaderBehaviour( LoaderBehaviourEnum value ) {
		relationColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSchemaDefLoaderBehaviour() {
		return( schemaDefLoaderBehaviour );
	}

	public void setSchemaDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		schemaDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSchemaRefLoaderBehaviour() {
		return( schemaRefLoaderBehaviour );
	}

	public void setSchemaRefLoaderBehaviour( LoaderBehaviourEnum value ) {
		schemaRefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getScopeLoaderBehaviour() {
		return( scopeLoaderBehaviour );
	}

	public void setScopeLoaderBehaviour( LoaderBehaviourEnum value ) {
		scopeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSecAppLoaderBehaviour() {
		return( secAppLoaderBehaviour );
	}

	public void setSecAppLoaderBehaviour( LoaderBehaviourEnum value ) {
		secAppLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSecDeviceLoaderBehaviour() {
		return( secDeviceLoaderBehaviour );
	}

	public void setSecDeviceLoaderBehaviour( LoaderBehaviourEnum value ) {
		secDeviceLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSecFormLoaderBehaviour() {
		return( secFormLoaderBehaviour );
	}

	public void setSecFormLoaderBehaviour( LoaderBehaviourEnum value ) {
		secFormLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSecGroupLoaderBehaviour() {
		return( secGroupLoaderBehaviour );
	}

	public void setSecGroupLoaderBehaviour( LoaderBehaviourEnum value ) {
		secGroupLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSecGroupFormLoaderBehaviour() {
		return( secGroupFormLoaderBehaviour );
	}

	public void setSecGroupFormLoaderBehaviour( LoaderBehaviourEnum value ) {
		secGroupFormLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSecGrpIncLoaderBehaviour() {
		return( secGrpIncLoaderBehaviour );
	}

	public void setSecGrpIncLoaderBehaviour( LoaderBehaviourEnum value ) {
		secGrpIncLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSecGrpMembLoaderBehaviour() {
		return( secGrpMembLoaderBehaviour );
	}

	public void setSecGrpMembLoaderBehaviour( LoaderBehaviourEnum value ) {
		secGrpMembLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSecSessionLoaderBehaviour() {
		return( secSessionLoaderBehaviour );
	}

	public void setSecSessionLoaderBehaviour( LoaderBehaviourEnum value ) {
		secSessionLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSecUserLoaderBehaviour() {
		return( secUserLoaderBehaviour );
	}

	public void setSecUserLoaderBehaviour( LoaderBehaviourEnum value ) {
		secUserLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getServerListFuncLoaderBehaviour() {
		return( serverListFuncLoaderBehaviour );
	}

	public void setServerListFuncLoaderBehaviour( LoaderBehaviourEnum value ) {
		serverListFuncLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getServerMethodLoaderBehaviour() {
		return( serverMethodLoaderBehaviour );
	}

	public void setServerMethodLoaderBehaviour( LoaderBehaviourEnum value ) {
		serverMethodLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getServerObjFuncLoaderBehaviour() {
		return( serverObjFuncLoaderBehaviour );
	}

	public void setServerObjFuncLoaderBehaviour( LoaderBehaviourEnum value ) {
		serverObjFuncLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getServerProcLoaderBehaviour() {
		return( serverProcLoaderBehaviour );
	}

	public void setServerProcLoaderBehaviour( LoaderBehaviourEnum value ) {
		serverProcLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getServiceLoaderBehaviour() {
		return( serviceLoaderBehaviour );
	}

	public void setServiceLoaderBehaviour( LoaderBehaviourEnum value ) {
		serviceLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getServiceTypeLoaderBehaviour() {
		return( serviceTypeLoaderBehaviour );
	}

	public void setServiceTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		serviceTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getStringColLoaderBehaviour() {
		return( stringColLoaderBehaviour );
	}

	public void setStringColLoaderBehaviour( LoaderBehaviourEnum value ) {
		stringColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getStringDefLoaderBehaviour() {
		return( stringDefLoaderBehaviour );
	}

	public void setStringDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		stringDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getStringTypeLoaderBehaviour() {
		return( stringTypeLoaderBehaviour );
	}

	public void setStringTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		stringTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSubProjectLoaderBehaviour() {
		return( subProjectLoaderBehaviour );
	}

	public void setSubProjectLoaderBehaviour( LoaderBehaviourEnum value ) {
		subProjectLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getSysClusterLoaderBehaviour() {
		return( sysClusterLoaderBehaviour );
	}

	public void setSysClusterLoaderBehaviour( LoaderBehaviourEnum value ) {
		sysClusterLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTSecGroupLoaderBehaviour() {
		return( tSecGroupLoaderBehaviour );
	}

	public void setTSecGroupLoaderBehaviour( LoaderBehaviourEnum value ) {
		tSecGroupLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTSecGrpIncLoaderBehaviour() {
		return( tSecGrpIncLoaderBehaviour );
	}

	public void setTSecGrpIncLoaderBehaviour( LoaderBehaviourEnum value ) {
		tSecGrpIncLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTSecGrpMembLoaderBehaviour() {
		return( tSecGrpMembLoaderBehaviour );
	}

	public void setTSecGrpMembLoaderBehaviour( LoaderBehaviourEnum value ) {
		tSecGrpMembLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTZDateColLoaderBehaviour() {
		return( tZDateColLoaderBehaviour );
	}

	public void setTZDateColLoaderBehaviour( LoaderBehaviourEnum value ) {
		tZDateColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTZDateDefLoaderBehaviour() {
		return( tZDateDefLoaderBehaviour );
	}

	public void setTZDateDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		tZDateDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTZDateTypeLoaderBehaviour() {
		return( tZDateTypeLoaderBehaviour );
	}

	public void setTZDateTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		tZDateTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTZTimeColLoaderBehaviour() {
		return( tZTimeColLoaderBehaviour );
	}

	public void setTZTimeColLoaderBehaviour( LoaderBehaviourEnum value ) {
		tZTimeColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTZTimeDefLoaderBehaviour() {
		return( tZTimeDefLoaderBehaviour );
	}

	public void setTZTimeDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		tZTimeDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTZTimeTypeLoaderBehaviour() {
		return( tZTimeTypeLoaderBehaviour );
	}

	public void setTZTimeTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		tZTimeTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTZTimestampColLoaderBehaviour() {
		return( tZTimestampColLoaderBehaviour );
	}

	public void setTZTimestampColLoaderBehaviour( LoaderBehaviourEnum value ) {
		tZTimestampColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTZTimestampDefLoaderBehaviour() {
		return( tZTimestampDefLoaderBehaviour );
	}

	public void setTZTimestampDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		tZTimestampDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTZTimestampTypeLoaderBehaviour() {
		return( tZTimestampTypeLoaderBehaviour );
	}

	public void setTZTimestampTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		tZTimestampTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTableLoaderBehaviour() {
		return( tableLoaderBehaviour );
	}

	public void setTableLoaderBehaviour( LoaderBehaviourEnum value ) {
		tableLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTableColLoaderBehaviour() {
		return( tableColLoaderBehaviour );
	}

	public void setTableColLoaderBehaviour( LoaderBehaviourEnum value ) {
		tableColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTenantLoaderBehaviour() {
		return( tenantLoaderBehaviour );
	}

	public void setTenantLoaderBehaviour( LoaderBehaviourEnum value ) {
		tenantLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTextColLoaderBehaviour() {
		return( textColLoaderBehaviour );
	}

	public void setTextColLoaderBehaviour( LoaderBehaviourEnum value ) {
		textColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTextDefLoaderBehaviour() {
		return( textDefLoaderBehaviour );
	}

	public void setTextDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		textDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTextTypeLoaderBehaviour() {
		return( textTypeLoaderBehaviour );
	}

	public void setTextTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		textTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTimeColLoaderBehaviour() {
		return( timeColLoaderBehaviour );
	}

	public void setTimeColLoaderBehaviour( LoaderBehaviourEnum value ) {
		timeColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTimeDefLoaderBehaviour() {
		return( timeDefLoaderBehaviour );
	}

	public void setTimeDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		timeDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTimeTypeLoaderBehaviour() {
		return( timeTypeLoaderBehaviour );
	}

	public void setTimeTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		timeTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTimestampColLoaderBehaviour() {
		return( timestampColLoaderBehaviour );
	}

	public void setTimestampColLoaderBehaviour( LoaderBehaviourEnum value ) {
		timestampColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTimestampDefLoaderBehaviour() {
		return( timestampDefLoaderBehaviour );
	}

	public void setTimestampDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		timestampDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTimestampTypeLoaderBehaviour() {
		return( timestampTypeLoaderBehaviour );
	}

	public void setTimestampTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		timestampTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTldLoaderBehaviour() {
		return( tldLoaderBehaviour );
	}

	public void setTldLoaderBehaviour( LoaderBehaviourEnum value ) {
		tldLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTokenColLoaderBehaviour() {
		return( tokenColLoaderBehaviour );
	}

	public void setTokenColLoaderBehaviour( LoaderBehaviourEnum value ) {
		tokenColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTokenDefLoaderBehaviour() {
		return( tokenDefLoaderBehaviour );
	}

	public void setTokenDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		tokenDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTokenTypeLoaderBehaviour() {
		return( tokenTypeLoaderBehaviour );
	}

	public void setTokenTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		tokenTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTopDomainLoaderBehaviour() {
		return( topDomainLoaderBehaviour );
	}

	public void setTopDomainLoaderBehaviour( LoaderBehaviourEnum value ) {
		topDomainLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getTopProjectLoaderBehaviour() {
		return( topProjectLoaderBehaviour );
	}

	public void setTopProjectLoaderBehaviour( LoaderBehaviourEnum value ) {
		topProjectLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUInt16ColLoaderBehaviour() {
		return( uInt16ColLoaderBehaviour );
	}

	public void setUInt16ColLoaderBehaviour( LoaderBehaviourEnum value ) {
		uInt16ColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUInt16DefLoaderBehaviour() {
		return( uInt16DefLoaderBehaviour );
	}

	public void setUInt16DefLoaderBehaviour( LoaderBehaviourEnum value ) {
		uInt16DefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUInt16TypeLoaderBehaviour() {
		return( uInt16TypeLoaderBehaviour );
	}

	public void setUInt16TypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		uInt16TypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUInt32ColLoaderBehaviour() {
		return( uInt32ColLoaderBehaviour );
	}

	public void setUInt32ColLoaderBehaviour( LoaderBehaviourEnum value ) {
		uInt32ColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUInt32DefLoaderBehaviour() {
		return( uInt32DefLoaderBehaviour );
	}

	public void setUInt32DefLoaderBehaviour( LoaderBehaviourEnum value ) {
		uInt32DefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUInt32TypeLoaderBehaviour() {
		return( uInt32TypeLoaderBehaviour );
	}

	public void setUInt32TypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		uInt32TypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUInt64ColLoaderBehaviour() {
		return( uInt64ColLoaderBehaviour );
	}

	public void setUInt64ColLoaderBehaviour( LoaderBehaviourEnum value ) {
		uInt64ColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUInt64DefLoaderBehaviour() {
		return( uInt64DefLoaderBehaviour );
	}

	public void setUInt64DefLoaderBehaviour( LoaderBehaviourEnum value ) {
		uInt64DefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUInt64TypeLoaderBehaviour() {
		return( uInt64TypeLoaderBehaviour );
	}

	public void setUInt64TypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		uInt64TypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getURLProtocolLoaderBehaviour() {
		return( uRLProtocolLoaderBehaviour );
	}

	public void setURLProtocolLoaderBehaviour( LoaderBehaviourEnum value ) {
		uRLProtocolLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUuidColLoaderBehaviour() {
		return( uuidColLoaderBehaviour );
	}

	public void setUuidColLoaderBehaviour( LoaderBehaviourEnum value ) {
		uuidColLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUuidDefLoaderBehaviour() {
		return( uuidDefLoaderBehaviour );
	}

	public void setUuidDefLoaderBehaviour( LoaderBehaviourEnum value ) {
		uuidDefLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUuidGenLoaderBehaviour() {
		return( uuidGenLoaderBehaviour );
	}

	public void setUuidGenLoaderBehaviour( LoaderBehaviourEnum value ) {
		uuidGenLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getUuidTypeLoaderBehaviour() {
		return( uuidTypeLoaderBehaviour );
	}

	public void setUuidTypeLoaderBehaviour( LoaderBehaviourEnum value ) {
		uuidTypeLoaderBehaviour = value;
	}

	public LoaderBehaviourEnum getValueLoaderBehaviour() {
		return( valueLoaderBehaviour );
	}

	public void setValueLoaderBehaviour( LoaderBehaviourEnum value ) {
		valueLoaderBehaviour = value;
	}

	// Parse a file

	public void parseFile( String url ) {
		parse( url );
	}
}
