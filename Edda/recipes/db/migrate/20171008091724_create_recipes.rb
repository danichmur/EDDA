class CreateRecipes < ActiveRecord::Migration[5.1]
  def change
    create_table :recipes do |t|
      t.string :name
      t.text :description
      t.string :img_url

      t.timestamps
    end
  end
end
