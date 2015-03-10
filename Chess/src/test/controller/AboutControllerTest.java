package controller;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import gui.AboutView;

import javax.swing.JButton;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import controller.AboutController.GoBackButtonListener;
import core.client.Model;
import core.client.Model.STATE;
import core.client.ProductInfo;

@RunWith(MockitoJUnitRunner.class)
public class AboutControllerTest 
{
	@Mock private AboutView view;
	@Mock private Model model;
	@Mock private ProductInfo info;
	@Mock private JButton button;
	@InjectMocks private AboutController controller;
	
	@Test
	public void testUpdate()
	{		
		when(model.getState()).thenReturn(STATE.ABOUT);
		when(model.getProductInformation()).thenReturn(info);
		
		controller.update();
		
		verify(view).setTitle(info.getTitle());
		verify(view).setDescription(info.getDescription());
		verify(view).setVersion(info.getVersion());
		verify(view).setCopyWrite(info.getCopywrite());
		verify(view).setAuthors(info.getAuthors());
		verify(view).setReleaseDate(info.getReleaseDate());
		verify(view).update();
	}
	
	@Test
	public void testNotUpdate()
	{
		when(model.getState()).thenReturn(STATE.INGAME);
		
		controller.update();
		
		verify(view, never()).update();
	}
	
	@Test
	public void testGoBackListener()
	{
		GoBackButtonListener l = controller.new GoBackButtonListener();
		l.actionPerformed(null);
		verify(model).setState(STATE.LOGIN);
	}
}
