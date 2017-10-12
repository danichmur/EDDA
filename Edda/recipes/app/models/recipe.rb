class Recipe < ApplicationRecord
  has_many :ingredients, dependent: :destroy
  accepts_nested_attributes_for :ingredients, allow_destroy: true
  
  def self.find_recipe(names, counts)
    recipes = []
    recipesAll = Recipe.all
    recipesAll.each do |recipe|
      if (names - recipe.ingredients.map(&:name)).empty?
        ingredients = recipe.ingredients
        for i in (0..(names.length - 1))
          break if (counts[i].to_i < ingredients.find_by_name(names[i]).count)
        end
        recipes.push(recipe) if (i == names.length - 1)
      end 
    end
    return recipes
  end

  def as_json(options={})
    super(:only => [:name,:description,:img_url],
          :include => {
            :ingredients => {:only => [:name, :count]}
          }
    )
  end
  
end
