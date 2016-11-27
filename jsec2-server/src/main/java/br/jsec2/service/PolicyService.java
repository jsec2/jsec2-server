package br.jsec2.service;

import org.springframework.stereotype.Service;

import br.jsec2.model.PolicyModel;

@Service
public class PolicyService {

	/**
	 * 
	 * 
	 * @param applicationId
	 * @param login
	 * @return
	 */
	public PolicyModel applyPolicies(Long applicationId, String login) {
		PolicyModel policyModel = new PolicyModel();

		return policyModel;
	}
}
