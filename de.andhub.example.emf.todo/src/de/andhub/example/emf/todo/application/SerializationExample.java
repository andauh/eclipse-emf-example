package de.andhub.example.emf.todo.application;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.andhub.example.emf.todo.Category;
import de.andhub.example.emf.todo.TodoEntry;
import de.andhub.example.emf.todo.TodoFactory;
import de.andhub.example.emf.todo.TodoPackage;
import de.andhub.example.emf.todo.TodoSystem;
import de.andhub.example.emf.todo.User;

public class SerializationExample {
	public static void main(String[] args) {
		TodoPackage.eINSTANCE.eClass();

		TodoFactory factory = TodoFactory.eINSTANCE;

		TodoSystem sys = factory.createTodoSystem();

		User user = factory.createUser();
		user.setId(0);
		user.setName("name");

		sys.getUsers().add(user);

		Category category = factory.createCategory();
		category.setName("category1");
		
		sys.getCategories().add(category);

		TodoEntry todo = factory.createTodoEntry();
		todo.setCategory(category);
		todo.setDescription("description");
		todo.setDueDate(ZonedDateTime.now());
		todo.setTitle("title");

		user.getTodoEntries().add(todo);

		Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> extensionToFactoryMap = registry.getExtensionToFactoryMap();
		extensionToFactoryMap.put("todo", new XMIResourceFactoryImpl());
		ResourceSet resourceSet = new ResourceSetImpl();

		Resource resource = resourceSet.createResource(URI.createURI("resources/serializedModelInstance.todo"));
		resource.getContents().add(sys);

		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
