package com.cesar.tcc.smartlibrary.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cesar.tcc.smartlibrary.iservice.UserProfileService;
import com.cesar.tcc.smartlibrary.model.UserProfile;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {

	@Autowired
	UserProfileService userProfileService;

	@Override
	public UserProfile convert(final Object element) {

		final Integer id = Integer.parseInt((String) element);
		final UserProfile profile = userProfileService.findById(id);
		return profile;
	}

}
