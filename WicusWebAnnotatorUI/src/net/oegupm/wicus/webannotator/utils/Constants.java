package net.oegupm.wicus.webannotator.utils;

public class Constants {
	
	public final static String reqsResource = "http://purl.org/net/wicus-reqs/resource/";
	public final static String svaResource = "http://purl.org/net/wicus-sva/resource/";
	public final static String whwResource = "http://purl.org/net/wicus-hwspecs/resource/";
	public final static String stacksResource = "http://purl.org/net/wicus-stack/resource/";

	public final static String resClassWorkflow = reqsResource + "Workflow/";
	public final static String resClassConcreteWorkflow = reqsResource + "ConcreteWorkflow/";
	public final static String resClassAbstractWorkflow = reqsResource + "AbstractWorkflow/";
	public final static String resClassWorkflowStep = reqsResource + "WorkflowStep/";
	public final static String resClassSoftwareRequirements = stacksResource + "SoftwareRequirements/";
	public final static String resClassSoftwareStack = stacksResource + "SoftwareStack/";
	public final static String resClassSoftwareComponent = stacksResource + "SoftwareComponent/";
	public final static String resClassSoftwareBinary = stacksResource + "SoftwareBinary/";
	public final static String resClassConfigurationInfo = stacksResource + "ConfigurationInfo/";
	public final static String resClassConfigurationParameter = stacksResource + "ConfigurationParameter/";
	public final static String resClassVersion = stacksResource + "Version/";

	public final static String ConcreteWorkflowSuffix = "_CONC_WF";
	public final static String AbstractWorkflowSuffix = "_ABS_WF";
	public final static String WorkflowSuffix = "_WF";
	public final static String ConfigurationInfoSuffix = "_CONF_INFO";
	public final static String WorkflowStepSuffix = "_WF_STEP";
	public final static String SoftwareRequirementsSuffix = "_SOFT_REQ";
	public final static String SoftwareStackSuffix = "_SOFT_STACK";
	public final static String SoftwareComponentSuffix = "_SOFT_COMP";
	public final static String VersionSuffix = "_VERSION";
	public final static String ConfigurationParameterSuffix = "_CONF_PAR";
	public final static String SoftwareBinarySuffix = "_SOFT_BIN";
	
	
	public final static String cnScientificVirtualAppliance = "http://purl.org/net/wicus-sva#ScientificVirtualAppliance";
	public final static String cnImageAppliance = "http://purl.org/net/wicus-sva#ImageAppliance";
	public final static String cnSoftwareStack = "http://purl.org/net/wicus-stack#SoftwareStack";
	public final static String cnSoftwareComponent= "http://purl.org/net/wicus-stack#SoftwareComponent";
	public final static String cnSoftwareRequirements = "http://purl.org/net/wicus-reqs#SoftwareRequirements";
	public final static String cnHardwareRequirements= "http://purl.org/net/wicus-reqs#HardwareRequirements";
	public final static String cnHardwareSpec= "http://purl.org/net/wicus-hwspecs#HardwareSpec";
	public final static String cnAbstractWorkflow = "http://purl.org/net/wicus-reqs#AbstractWorkflow";
	public final static String cnWorkflowStep = "http://purl.org/net/wicus-reqs#WorkflowStep";
	public final static String cnExecutionEnvironment = "http://purl.org/net/wicus-reqs#ExecutionEnvironment";
	public final static String cnRequirementLevel = "http://purl.org/net/wicus-reqs#RequirementLevel";
	public final static String cnConcreteWorkflow = "http://purl.org/net/wicus-reqs#ConcreteWorkflow";
	public final static String cnWorkflow = "http://purl.org/net/wicus-reqs#Workflow";
	public final static String cnConfigurationInfo = "http://purl.org/net/wicus-stack#ConfigurationInfo";
	public final static String cnDeploymentStep = "http://purl.org/net/wicus-stack#DeploymentStep";
	public final static String cnVersion = "http://purl.org/net/wicus-stack#Version";
	public final static String cnSoftwareBinary= "http://purl.org/net/wicus-stack#SoftwareBinary";
	public final static String cnOpenLicense= "http://purl.org/net/wicus-stack#OpenLicense";
	public final static String cnLicense= "http://purl.org/net/wicus-stack#License";
	public final static String cnConfigurationParameter= "http://purl.org/net/wicus-stack#ConfigurationParameter";
	public final static String cnDeploymentScript= "http://purl.org/net/wicus-stack#DeploymentScript";
	public final static String cnNotOpenLicense= "http://purl.org/net/wicus-stack#NotOpenLicense";
	public final static String cnDeploymentPlan= "http://purl.org/net/wicus-stack#DeploymentPlan";
	public final static String cnVariable= "http://purl.org/net/p-plan#Variable";
	public final static String cnStorage= "http://purl.org/net/wicus-hwspecs#Storage";
	public final static String cnNetwork= "http://purl.org/net/wicus-hwspecs#Network";
	public final static String cnMemory= "http://purl.org/net/wicus-hwspecs#Memory";
	public final static String cnHardwareComponent= "http://purl.org/net/wicus-hwspecs#HardwareComponent";
	public final static String cnFeature= "http://purl.org/net/wicus-hwspecs#Feature";
	public final static String cnCPU= "http://purl.org/net/wicus-hwspecs#CPU";
	public final static String cnVMImage= "http://purl.org/net/wicus-sva#VMImage";
	public final static String cnInfrastructureProvider= "http://purl.org/net/wicus-sva#InfrastructureProvider";
	
	public final static String cnPPlanPlan = "http://purl.org/net/p-plan#Plan";
	public final static String cnPPlanStep = "http://purl.org/net/p-plan#Step";
	public final static String cnPPlanVariable = "http://purl.org/net/p-plan#Variable";
	
	public final static String opnrequiresHardware = "http://purl.org/net/wicus#requiresHardware";
	public final static String opnhasSoftwareStack = "http://purl.org/net/wicus#hasSoftwareStack";
	public final static String opnhasHardwareSpecs = "http://purl.org/net/wicus#hasHardwareSpecs";
	public final static String opncomposedBySoftwareStack = "http://purl.org/net/wicus#composedBySoftwareStack";
	public final static String opncomposedByHardwareSpec = "http://purl.org/net/wicus#composedByHardwareSpec";
	public final static String opnrequires = "http://purl.org/net/wicus-reqs#requires";
	public final static String opnisStepOfWorkflow = "http://purl.org/net/wicus-reqs#isStepOfWorkflow";
	public final static String opnhasSubworkflow = "http://purl.org/net/wicus-reqs#hasSubworkflow";
	public final static String opnhasLevel = "http://purl.org/net/wicus-reqs#hasLevel";
	public final static String opnhasConcreteWorkflow = "http://purl.org/net/wicus-reqs#hasConcreteWorkflow";
	public final static String opnhasLicense = "http://purl.org/net/wicus-stack#hasLicense";
	public final static String opnhasBinary = "http://purl.org/net/wicus-stack#hasBinary";
	public final static String opnhasConfigurationInfo = "http://purl.org/net/wicus-stack#hasConfigurationInfo";
	public final static String opnhasVersion = "http://purl.org/net/wicus-stack#hasVersion";
	public final static String opnhasHigherVersion = "http://purl.org/net/wicus-stack#hasHigherVersion";
	public final static String opnlower = "http://purl.org/net/wicus-stack#lower";
	public final static String opnhigher = "http://purl.org/net/wicus-stack#higher";
	public final static String opnhasLowerVersion = "http://purl.org/net/wicus-stack#hasLowerVersion";
	public final static String opnisConfigurationParameterOf = "http://purl.org/net/wicus-stack#isConfigurationParameterOf";
	public final static String opnisDeploymentPlanOf = "http://purl.org/net/wicus-stack#isDeploymentPlanOf";
	public final static String opnisDeploymentStepOf = "http://purl.org/net/wicus-stack#isDeploymentStepOf";
	public final static String opnhasDeploymentScript = "http://purl.org/net/wicus-stack#hasDeploymentScript";
	public final static String opndependsOn = "http://purl.org/net/wicus-stack#dependsOn";
	public final static String opnnextStep = "http://purl.org/net/wicus-stack#nextStep";
	public final static String opnhasSoftwareComponent = "http://purl.org/net/wicus-stack#hasSoftwareComponent";
	public final static String opnnextComponent = "http://purl.org/net/wicus-stack#nextComponent";
	public final static String opnhasDeploymentInfo = "http://purl.org/net/wicus-stack#hasDeploymentInfo";
	public final static String opnhasHardwareComponent = "http://purl.org/net/wicus-hwspecs#hasHardwareComponent";
	public final static String opnhasFeature = "http://purl.org/net/wicus-hwspecs#hasFeature";
	public final static String opnprovidedBy = "http://purl.org/net/wicus-sva#providedBy";
	public final static String opnisSupportedBy = "http://purl.org/net/wicus-sva#isSupportedBy";
	public final static String opnhasVMImage = "http://purl.org/net/wicus-sva#hasVMImage";	
	
	public final static String opnisStepOfPlan = "http://purl.org/net/p-plan#isStepOfPlan";
	public final static String opnisInputVarOf = "http://purl.org/net/p-plan#isInputVarOf";
	public final static String opndcrequires = "http://purl.org/dc/terms/requires";
	

	public final static String dpnversionNumber = "http://purl.org/net/wicus-stack#versionNumber";	
	public final static String dpnvmId = "http://purl.org/net/wicus-sva#vmId";
	public final static String dpnscript = "http://purl.org/net/wicus-stack#script";
	public final static String dpnparameterValue = "http://purl.org/net/wicus-stack#parameterValue";
	public final static String dpnparameterName = "http://purl.org/net/wicus-stack#parameterName";
	public final static String dpnbinary = "http://purl.org/net/wicus-stack#binary";
	public final static String dpnvalue = "http://purl.org/net/wicus-hwspecs#value";
	public final static String dpnunit = "http://purl.org/net/wicus-hwspecs#unit";	

}
