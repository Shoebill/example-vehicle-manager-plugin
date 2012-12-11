package net.gtaun.shoebill.example.vm.dialog;

import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;

public class AbstractPageListDialog extends AbstractListDialog
{
	private int itemsPerPage = 10;
	private int currentPage;
	

	protected AbstractPageListDialog(Player player, Shoebill shoebill, EventManager eventManager)
	{
		super(player, shoebill, eventManager);
	}
	
	public int getItemsPerPage()
	{
		return itemsPerPage;
	}
	
	public void setItemsPerPage(int itemsPerPage)
	{
		this.itemsPerPage = itemsPerPage;
	}
	
	public int getCurrentPage()
	{
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}
	
	public int getMaxPage()
	{
		return (dialogListItems.size()-1) / itemsPerPage;
	}
	
	@Override
	public void show()
	{
		String listStr = "";
		displayedItems.clear();
		
		displayedItems.add(new DialogListItem("<< Prev Page <<")
		{
			@Override
			public void onItemSelect()
			{
				if (currentPage > getMaxPage()) currentPage = getMaxPage();
				if (currentPage > 0) currentPage--;
				show();
			}
		});
		
		int offset = itemsPerPage * currentPage;
		for (int i=0; i<itemsPerPage; i++)
		{
			int index = offset+i;
			if (dialogListItems.size() <= index)
			{
				displayedItems.add(new DialogListItem(" - ")
				{
					@Override
					public void onItemSelect()
					{
						show();
					}
				});
				continue;
			}
			
			DialogListItem item = dialogListItems.get(offset+i);
			displayedItems.add(item);
		}
		
		displayedItems.add(new DialogListItem(">> Next Page >>")
		{
			@Override
			public void onItemSelect()
			{
				if (currentPage > getMaxPage()) currentPage = getMaxPage();
				if (currentPage < getMaxPage()) currentPage++;
				show();
			}
		});

		for (DialogListItem item : displayedItems)
		{
			listStr += item.toItemString() + "\n";
		}
		show(listStr);
	}
}
