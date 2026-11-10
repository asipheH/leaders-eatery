use leaders_catalog_db;

insert into categories(category_name)
values
    ('Burgers'),
    ('Pizza'),
    ('Healthy Meals'),
    ('Drinks'),
    ('Desserts'),
    ('Pasta'),
    ('Grilled Food');


insert into restaurants(name, description, cuisine, address, image_url, active)
values(
    'Leaders Grill',
    'Delicious burgers and freshly griled meals.',
    'American',
    'Pretoria, South Africa',
    'https://images.unsplash.com/photo-1568901346375-23c9450c58cd',
    TRUE
),

(
    'Italian Corner',
    'Authentic Italian-inspired pizza and pasta meals.',
    'Italian',
    'Pretoria, South Africa',
    'https://images.unsplash.com/photo-1513104890138-7c749659a591',
    TRUE
),

(
    'Fresh Bites',
    'Fresh, healthy and nutritious meals.',
    'Healthy',
    'Pretoria, South Africa',
    'https://images.unsplash.com/photo-1512621776951-a57141f2eefd',
    TRUE
);

insert into dishes(restaurant_id, category_id, name, description,price, image_url, available)
values
(1,1,'Classic Beef Burger', 'Juicy beef patty with lettuce, tomato and cheese.', 85.00, 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd', TRUE),

(1,7,'Grilled Chicken', 'Tender grilled chicken served with vegetables.', 110.00, 'https://images.unsplash.com/photo-1532550907401-a500c9a57435', TRUE),

(1,4,'Fresh Lemonade', 'Refreshing homemade lemonade.', 30.00, 'https://images.unsplash.com/photo-1513558161293-cdaf765edfd4', TRUE);


insert into dishes(restaurant_id, category_id, name, description,price, image_url, available)
values
(2,2,'Margherita Pizza', 'Classic pizza with tomato, mozzarella and basil.', 95.00, 'https://images.unsplash.com/photo-1574071318508-1cdbab80d002', TRUE),

(2,6,'Creamy Alfredo Pizza', 'Creamy pasta with parmesan sauce.', 105.00, 'https://images.unsplash.com/photo-1473093295043-cdd812d0e601', TRUE),

(2,5,'Chocolate Cake', 'Rich chocolate dessert.', 45.00, 'https://images.unsplash.com/photo-1578985545062-69928b1d9587', TRUE);


insert into dishes(restaurant_id, category_id, name, description,price, image_url, available)
values
(3,3,'Chicken Salad Bowl', 'Fresh salad with grilled chicken..', 90.00, 'https://images.unsplash.com/photo-1512621776951-a57141f2eefd', TRUE),

(3,3,'Vegetable Wrap', 'Fresh vegetables wrapped in a soft tortilla.', 65.00, 'https://images.unsplash.com/photo-1626700051175-6818013e1d4f', TRUE),

(3,4,'Fruit Smoothie', 'Fresh blended seasonal fruit.', 40.00, 'https://images.unsplash.com/photo-1505252585461-04db1eb84625', TRUE);