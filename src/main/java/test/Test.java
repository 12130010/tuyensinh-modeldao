package test;

import org.springframework.web.client.RestTemplate;

import com.nhuocquy.model.Account;

public class Test {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		Account page = restTemplate
				.getForObject(
						"http://192.168.137.1:8080/tuyensinh/login?username=nhuocquy&password=nhuocquy",
						Account.class);
		System.out.println(page);
	}
}
