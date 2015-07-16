package tw.android;

import java.util.ArrayList;

public class ListItem {

	private String title, thumbnailUrl,attributes;
	private int attack;
	private ArrayList<String> material;

	public ListItem() {
	}

	public ListItem(String name, String thumbnailUrl, int attack, String attributes,
			ArrayList<String> material) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.attack = attack;
		this.attributes = attributes;
		this.material = material;
	}

	/* 名稱 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	/* 縮圖 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	/* 攻擊力 */
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	/* 屬性 */
	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	/* 材料 */
	public ArrayList<String> getMaterial() {
		return material;
	}

	public void setMaterial(ArrayList<String> material) {
		this.material = material;
	}

}
