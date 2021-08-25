package hello_world.portlet;

import hello_world.constants.Hello_worldPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.List;

/**
 * @author dima
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Hello_world",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + Hello_worldPortletKeys.HELLO_WORLD,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class Hello_worldPortlet extends MVCPortlet {


//	User: firstname, lastname, email
//	List<User> - initialuze with hardcoded values
//	render on jsp
//List<UserEntity> users = UsersMock.getUsers();
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		List<UserEntity> users = UsersMock.getUsers();
		renderRequest.setAttribute("usersmock", users);
		super.render(renderRequest, renderResponse);
	}


	@Activate
	public void asdasd(){
		System.out.println("asdasd");
	}
}