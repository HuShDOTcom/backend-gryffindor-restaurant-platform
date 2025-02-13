package pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SignInCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
  Optional<ImmutablePair<User, String>> handle(SignInCommand command);
  Optional<User> handle(SignUpCommand command);
}
