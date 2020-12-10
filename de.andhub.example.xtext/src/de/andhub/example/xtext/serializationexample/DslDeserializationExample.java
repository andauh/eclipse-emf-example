package de.andhub.example.xtext.serializationexample;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import de.andhub.example.emf.todo.TodoPackage;
import de.andhub.example.emf.todo.TodoSystem;
import de.andhub.example.xtext.TodoDslStandaloneSetup;

public class DslDeserializationExample {

	public static void main(String[] args) {
		TodoPackage.eINSTANCE.eClass();
		new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
		Injector injector = new TodoDslStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		Resource resource = resourceSet.getResource(URI.createURI("resources/serializedDslFile.tododsl"), true);
		TodoSystem model = (TodoSystem) resource.getContents().get(0);
		System.out.println(model);
		
	}
}
