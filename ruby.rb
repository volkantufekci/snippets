Ruby Style Guide => https://github.com/styleguide/ruby

# remove all whitespaces
" hede hodo ".delete(' ')     # "hedehodo", so this one is simpler than the regexp one below

" hede hodo ".gsub(/\s+/, "") # "hedehodo"

# returns given value if given key is not found in the hash
hash.fetch(:order, :desc) # returns :desc if hash[:order] does not exist


