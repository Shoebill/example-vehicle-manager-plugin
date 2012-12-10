package net.gtaun.shoebill.example.vm.dialog;

import java.util.ArrayList;
import java.util.Collection;

import net.gtaun.shoebill.SampObjectFactory;
import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.constant.DialogStyle;
import net.gtaun.shoebill.event.DialogEventHandler;
import net.gtaun.shoebill.event.dialog.DialogCancelEvent;
import net.gtaun.shoebill.event.dialog.DialogResponseEvent;
import net.gtaun.shoebill.object.Dialog;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;
import net.gtaun.util.event.EventManager.HandlerEntry;
import net.gtaun.util.event.EventManager.HandlerPriority;

public abstract class AbstractDialog
{
	protected final Shoebill shoebill;
	protected final EventManager eventManager;
	protected final Player player;
	
	private final Dialog dialog;
	private final DialogStyle style;
	
	private final Collection<HandlerEntry> eventHandlerEntries;
	
	private String caption = "None";
	private String buttonOk = "OK";
	private String buttonCancel = "Cancel";
	
	
	protected AbstractDialog(DialogStyle style, Player player, Shoebill shoebill, EventManager eventManager)
	{
		this.style = style;
		this.shoebill = shoebill;
		this.eventManager = eventManager;
		this.player = player;
		
		SampObjectFactory factory = shoebill.getSampObjectFactory();
		dialog = factory.createDialog();
		
		eventHandlerEntries = new ArrayList<>();
		eventHandlerEntries.add(eventManager.addHandler(DialogResponseEvent.class, dialog, dialogEventHandler, HandlerPriority.NORMAL));
		eventHandlerEntries.add(eventManager.addHandler(DialogCancelEvent.class, dialog, dialogEventHandler, HandlerPriority.NORMAL));
	}
	
	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		
		for (HandlerEntry entry : eventHandlerEntries) entry.cancel();
	}
	
	private DialogEventHandler dialogEventHandler = new DialogEventHandler()
	{
		public void onDialogResponse(DialogResponseEvent event)
		{
			AbstractDialog.this.onDialogResponse(event);
		}
		
		public void onDialogCancel(DialogCancelEvent event)
		{
			AbstractDialog.this.onDialogCancel(event);
		}
	};
	
	public void setCaption(String caption)
	{
		this.caption = caption;
	}
	
	public void setButtonOk(String buttonOk)
	{
		this.buttonOk = buttonOk;
	}
	
	public void setButtonCancel(String buttonCancel)
	{
		this.buttonCancel = buttonCancel;
	}
	
	protected void show(String text)
	{
		player.showDialog(dialog, style, caption, text, buttonOk, buttonCancel);
	}
	
	public abstract void show();
	
	protected void onDialogResponse(DialogResponseEvent event)
	{
		
	}
	
	protected void onDialogCancel(DialogCancelEvent event)
	{
		
	}
}
