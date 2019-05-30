package com.crmpetproject.crmpetproject.configuration.initializer;

import com.crmpetproject.crmpetproject.models.*;
import com.crmpetproject.crmpetproject.servives.interfaces.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DataInitializer {

	@Autowired
	private StatusService statusService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SocialProfileTypeService socialProfileTypeService;

	@Autowired
	private ClientHistoryService clientHistoryService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentStatusService studentStatusService;

	private void init() {

        // DEFAULT STATUS AND FIRST STATUS FOR RELEASE
        Status defaultStatus = new Status("Deleted", true, 5L, false, 0, 0);
        Status status0 = new Status("New clients", false, 1L, false, 0, 0);

		Role roleAdmin = new Role("ADMIN");
		Role roleOwner = new Role("OWNER");
		Role roleUser = new Role("USER");
		Role roleMentor = new Role("MENTOR");
		roleService.add(roleAdmin);
		roleService.add(roleUser);
		roleService.add(roleOwner);
		roleService.add(roleMentor);

        SocialProfileType VK = new SocialProfileType("vk");
        SocialProfileType FACEBOOK = new SocialProfileType("facebook");
        SocialProfileType UNKNOWN = new SocialProfileType("unknown");
        socialProfileTypeService.add(VK);
        socialProfileTypeService.add(FACEBOOK);
        socialProfileTypeService.add(UNKNOWN);

        User admin = new User(
                "Stanislav",
                "Sorokin",
                "88062334088",
                "admin@mail.ru",
                "admin",
                null, Client.Sex.MALE.toString(),
                "Moscow",
                "Russia",
                Arrays.asList(roleService.getRoleByName("USER"), roleService.getRoleByName("ADMIN"),
                        roleService.getRoleByName("OWNER")),
                true);
        admin.setAutoAnswer("Admin: Предлагаем вам пройти обучение на нашем сайте");
        userService.add(admin);

        User user1 = new User("Ivan", "Ivanov", "79123456789", "user1@mail.ru",
                "user", null, Client.Sex.MALE.toString(), "Minsk", "Belarus", Collections.singletonList(roleService.getRoleByName("USER")), true);
        userService.add(user1);

        User user2 = new User("Petr", "Petrov", "89118465234", "user2@mail.ru",
                "user", null, Client.Sex.MALE.toString(), "Tver", "Russia", Collections.singletonList(roleService.getRoleByName("USER")), true);
        userService.add(user2);

		User user3 = new User("Vlad", "Mentor", "89118465234", "user3@mail.ru",
				"user", null, Client.Sex.MALE.toString(), "Tver", "Russia", Arrays.asList(roleService.getRoleByName("USER"), roleService.getRoleByName("MENTOR")), true);
		userService.add(user3);

		User user4 = new User("Nikita", "Mentor", "89118465234", "eefilee@gmail.com",
				"user", null, Client.Sex.MALE.toString(), "Tver", "Russia", Arrays.asList(roleService.getRoleByName("USER"), roleService.getRoleByName("MENTOR")), true);
		userService.add(user4);

		User user5 = new User("Benedikt", "Manager", "9999999999", "qqfilqq@gmail.com",
				"user", null, Client.Sex.MALE.toString(), "Tver", "Russia", Arrays.asList(roleService.getRoleByName("USER"), roleService.getRoleByName("ADMIN"),
				roleService.getRoleByName("OWNER")), true);
		userService.add(user5);

        Status status1 = new Status("In processing", false, 2L, true, 3, 33);
        Status status2 = new Status("Approval", false, 3L, true, 0, 30);
        Status status3 = new Status("On pause", false, 4L, false, 0, 0);
        Status status4 = new Status("Dropout", false, 6L, false, 0, 0);

		Client client1 = new Client("Юрий", "Долгоруков", "79999992288", "u.dolg@mail.ru", (byte) 21, Client.Sex.MALE, "Тула", "Россия", Client.State.FINISHED, ZonedDateTime.now());
		Client client2 = new Client("Вадим", "Бойко", "89687745632", "vboyko@mail.ru", (byte) 33, Client.Sex.MALE, "Тула", "Россия", Client.State.LEARNING, ZonedDateTime.ofInstant(Instant.now().minusMillis(200000000), ZoneId.systemDefault()));
		Client client3 = new Client("Александра", "Соловьева", "78300029530", "a.solo@mail.ru", (byte) 53, Client.Sex.FEMALE, "Тула", "Россия", Client.State.LEARNING, ZonedDateTime.ofInstant(Instant.now().minusMillis(300000000), ZoneId.systemDefault()));
		Client client4 = new Client("Иван", "Федоров", "78650824705", "i.fiod@mail.ru", (byte) 20, Client.Sex.MALE, "Тула", "Россия", Client.State.NEW, ZonedDateTime.ofInstant(Instant.now().minusMillis(400000000), ZoneId.systemDefault()));
		client1.addHistory(clientHistoryService.createHistory("инициализации crm"));
		client2.addHistory(clientHistoryService.createHistory("инициализации crm"));
		client3.addHistory(clientHistoryService.createHistory("инициализации crm"));
		client4.addHistory(clientHistoryService.createHistory("инициализации crm"));
		client1.setSocialProfiles(Arrays.asList(new SocialProfile("https://vk.com/id", socialProfileTypeService.getByTypeName("vk")),
				new SocialProfile("https://fb.com/id", socialProfileTypeService.getByTypeName("facebook"))));
		client2.setSocialProfiles(Arrays.asList(new SocialProfile("https://vk.com/id", socialProfileTypeService.getByTypeName("vk")),
				new SocialProfile("https://fb.com/id", socialProfileTypeService.getByTypeName("facebook"))));
		client3.setSocialProfiles(Arrays.asList(new SocialProfile("https://vk.com/id", socialProfileTypeService.getByTypeName("vk")),
				new SocialProfile("https://fb.com/id", socialProfileTypeService.getByTypeName("facebook"))));
		client4.setSocialProfiles(Arrays.asList(new SocialProfile("https://vk.com/id", socialProfileTypeService.getByTypeName("vk")),
				new SocialProfile("https://fb.com/id", socialProfileTypeService.getByTypeName("facebook"))));
		client1.setJobs(Arrays.asList(new Job("javaMentor", "developer"), new Job("Microsoft", "Junior developer")));

		clientService.addClient(client1);
		clientService.addClient(client2);
		clientService.addClient(client3);
		clientService.addClient(client4);
		status0.addClient(clientService.getClientByEmail("u.dolg@mail.ru"));
		status1.addClient(clientService.getClientByEmail("i.fiod@mail.ru"));
		status2.addClient(clientService.getClientByEmail("vboyko@mail.ru"));
		status3.addClient(clientService.getClientByEmail("a.solo@mail.ru"));
		statusService.addInit(status0);
		statusService.addInit(status1);
		statusService.addInit(status2);
		statusService.addInit(status3);
		statusService.addInit(status4);
		statusService.addInit(defaultStatus);

        StudentStatus trialStatus = studentStatusService.add(new StudentStatus("Big"));
        StudentStatus learningStatus = studentStatusService.add(new StudentStatus("Middle"));
        StudentStatus pauseStatus = studentStatusService.add(new StudentStatus("Small"));

        Student trialStudent = new Student(clientService.getClientByEmail("i.fiod@mail.ru"), LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3), new BigDecimal(12000.00), new BigDecimal(8000.00), new BigDecimal(4000.00), trialStatus, "На пробных");
        Student learningStudent = new Student(clientService.getClientByEmail("vboyko@mail.ru"), LocalDateTime.now(), LocalDateTime.now().plusDays(30), new BigDecimal(12000.00), new BigDecimal(8000.00), new BigDecimal(4000.00), learningStatus, "Быстро учится");
        Student pauseStudent = new Student(clientService.getClientByEmail("a.solo@mail.ru"), LocalDateTime.now(), LocalDateTime.now().plusDays(14), new BigDecimal(12000.00), new BigDecimal(12000.00), new BigDecimal(0.00), pauseStatus, "Уехал в отпуск на 2 недели");
        studentService.add(trialStudent);
        studentService.add(learningStudent);
        studentService.add(pauseStudent);


		//TODO удалить после теста

		Faker faker = new Faker();
		List<Client> list = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			Client client = new Client(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().phoneNumber(), "teststatususer" + i + "@gmail.com", (byte) 20, Client.Sex.MALE, status2);
			client.addHistory(clientHistoryService.createHistory("инициализация crm"));
			list.add(client);
		}
		clientService.addBatchClients(list);
		list.clear();

    }
}
