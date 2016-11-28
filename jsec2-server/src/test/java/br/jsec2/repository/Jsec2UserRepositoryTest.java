package br.jsec2.repository;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.jsec2.domain.Jsec2User;
import br.jsec2.domain.Property;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class Jsec2UserRepositoryTest {

	@Autowired
	private Jsec2UserRepository jsec2UserRepository;

	@Test
	public void insertTest() {
		Jsec2User jsec2User = new Jsec2User();
		jsec2User.setName("JOSÉ RIBAMAR MONTEIRO DA CRUZ");
		jsec2User.setLogin("jcruz");
		jsec2User.setEnabled(1);
		jsec2User.setPasswd("123");

		Map<String, Object> properties = new HashMap<>();
		properties.put("FUNCAO", "CHEFE_SECAO");
		properties.put("EMAIL", "jcruz@tre-pa.gov.br");

		jsec2User.setProperties(new Property(properties));
		
		jsec2UserRepository.save(jsec2User);
	}
}
