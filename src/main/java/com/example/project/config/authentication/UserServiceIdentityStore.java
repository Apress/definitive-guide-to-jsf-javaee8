package com.example.project.config.authentication;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import com.example.project.model.User;
import com.example.project.service.UserService;

@CustomFormAuthenticationMechanismDefinition(
	loginToContinue = @LoginToContinue(
		loginPage = "/login",
		errorPage = ""
	)
)
@ApplicationScoped
public class UserServiceIdentityStore implements IdentityStore {

	@Inject
	private UserService userService;

	@Override
	public CredentialValidationResult validate(Credential credential) {
		Optional<User> optionalUser = findUser(credential);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			return new CredentialValidationResult(new UserPrincipal(user), user.getRolesAsStrings());
		}
		else {
			return CredentialValidationResult.INVALID_RESULT;
		}
	}

	private Optional<User> findUser(Credential credential) {
		if (credential instanceof UsernamePasswordCredential) {
			String email = ((UsernamePasswordCredential) credential).getCaller();
			String password = ((UsernamePasswordCredential) credential).getPasswordAsString();
			return userService.findByEmailAndPassword(email, password);
		}
		else if (credential instanceof CallerOnlyCredential) {
			String email = ((CallerOnlyCredential) credential).getCaller();
			return userService.findByEmail(email);
		}
		else {
			throw new UnsupportedOperationException(credential.getClass().getName());
		}
	}

}
