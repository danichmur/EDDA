class Recipe < ApplicationRecord
  has_many :ingredients, dependent: :destroy
  accepts_nested_attributes_for :ingredients, allow_destroy: true
  
  def as_json(options={})
    super(:only => [:name,:description,:img_url],
          :include => {
            :ingredients => {:only => [:name, :count]}
          }
    )
  end
  
end
