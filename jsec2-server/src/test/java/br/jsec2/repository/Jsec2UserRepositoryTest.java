package br.jsec2.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.jsec2.domain.Jsec2User;
import br.jsec2.domain.Property;
import br.jsec2.domain.PropertyItem;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class Jsec2UserRepositoryTest {

	@Autowired
	private Jsec2UserRepository jsec2UserRepository;

	//@Test
	public void insertTest() {
		Jsec2User jsec2User = new Jsec2User();
		jsec2User.setName("JOSÉ RIBAMAR MONTEIRO DA CRUZ");
		jsec2User.setLogin("jcruz");
		jsec2User.setEnabled(1);
		jsec2User.setPasswd("123");

		Property properties = new Property();
		properties.getProperties().add(new PropertyItem("FUNCAO", "CHEFE_SECAO"));
		properties.getProperties().add(new PropertyItem("EMAIL", "jcruz@tre-pa.gov.br"));
		jsec2User.setProperties(properties);

		jsec2UserRepository.save(jsec2User);

		Jsec2User jsec2User2 = jsec2UserRepository.findOne(jsec2User.getId());
		System.out.println(jsec2User2.getProperties());
	}
	
	@Test
	public void readTest() {
		Jsec2User jsec2User2 = jsec2UserRepository.findOne(27L);
		System.out.println(jsec2User2.getProperties());
	}
}
