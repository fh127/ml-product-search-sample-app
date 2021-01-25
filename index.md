# ML Product Search Sample App

## Description
Sample App to search products using Mercado Libre Api.

Application that uses Mercadolibre's APIs, with following sections:
* Sites:  available Mercado Libre sites to search products
* Search: product search logic and answer list.
* Product page: detail of a product (which should be accessible by tapping one
of the items in the search result).

### Mercado Libre Api

* Sites: this end point was used to get sites in the sample app
```markdown
   https://api.mercadolibre.com/sites
```

* Product Search: this end point was used to get search results in the sample app
```markdown
   https://api.mercadolibre.com/sites/{site}/search?q={query}
```

* Product Details: these end points were used to get product details in the sample app
```markdown
   https://api.mercadolibre.com/items/{itemId}

   https://api.mercadolibre.com/users/{sellerId}
```

Documentation related to the [Mercado Libre api](https://developers.mercadolibre.com.ar/es_ar/items-y-busquedas#Obtener-%C3%ADtems-de-una-consulta-de-b%C3%BAsqueda)


Markdown is a lightweight and easy-to-use syntax for styling your writing. It includes conventions for

```markdown
Syntax highlighted code block

# Header 1
## Header 2
### Header 3

- Bulleted
- List

1. Numbered
2. List

**Bold** and _Italic_ and `Code` text

[Link](url) and ![Image](src)
```

For more details see [GitHub Flavored Markdown](https://guides.github.com/features/mastering-markdown/).

### Jekyll Themes

Your Pages site will use the layout and styles from the Jekyll theme you have selected in your [repository settings](https://github.com/fh127/ml-product-search-sample-app/settings). The name of this theme is saved in the Jekyll `_config.yml` configuration file.

### Support or Contact

Having trouble with Pages? Check out our [documentation](https://docs.github.com/categories/github-pages-basics/) or [contact support](https://support.github.com/contact) and we’ll help you sort it out.
