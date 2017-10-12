Rails.application.routes.draw do
  resources :recipes do
    get :find_recipe, on: :collection
  end
  
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
