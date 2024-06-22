package YU.PinforYouAPIServer.Other;

import java.util.HashMap;
import java.util.Map;

public class CardRecommendation {

    // 카드 할인율 계산 함수
    public static double calculateDiscount(int cardType, String category, double amount) {
        double discount = 0;
//              ---------------------------신한------------------------------------
        switch (cardType) {
            case 1:
                if (category.equals("commercial_restaurant")) {
                    discount = Math.min(amount * 0.10, 1000);
                } else if (category.equals("fastfood_restaurant")) {
                    discount = 0;
                } else if (category.equals("convenience_store")) {
                    discount = Math.min(amount * 0.10, 1000);
                } else if (category.equals("cu")) {
                    discount = Math.min(amount * 0.10, 1000);
                } else if (category.equals("cafe")) {
                    discount = Math.min(amount * 0.10, 1000);
                } else if (category.equals("starbucks")) {
                    discount = Math.min(amount * 0.10, 1000);
                } else if (category.equals("supermarket")) {
                    discount = Math.min(amount * 0.10, 5000);
                } else if (category.equals("hospital")) {
                    discount = Math.min(amount * 0.10, 1000);
                } else if (category.equals("pharmacy")) {
                    discount = Math.min(amount * 0.10, 1000);
                }
                // 추가 계산식 작성 필요
                break;
            case 2:
                if (category.equals("commercial_restaurant")) {
                    discount = amount * 0.0070;
                } else if (category.equals("fastfood_restaurant")) {
                    discount = 0;
                } else if (category.equals("convenience_store")) {
                    discount = amount * 0.0210;
                } else if (category.equals("cu")) {
                    discount = amount * 0.0210;
                } else if (category.equals("cafe")) {
                    discount = amount * 0.0210;
                } else if (category.equals("starbucks")) {
                    discount = amount * 0.0210;
                } else if (category.equals("supermarket")) {
                    discount = amount * 0.0210;
                } else if (category.equals("hospital")) {
                    discount = amount * 0.0070;
                } else if (category.equals("pharmacy")) {
                    discount = amount * 0.0070;
                }
                // 추가 계산식 작성 필요
                break;
            case 3:
                if (category.equals("commercial_restaurant")) {
                    discount = 0;
                } else if (category.equals("fastfood_restaurant")) {
                    discount = 0;
                } else if (category.equals("convenience_store")) {
                    discount = 0;
                } else if (category.equals("cu")) {
                    discount = 0;
                } else if (category.equals("cafe")) {
                    discount = 0;
                } else if (category.equals("starbucks")) {
                    if (amount >= 7800) {
                        discount = 3900;
                    }
                } else if (category.equals("supermarket")) {
                    discount = Math.min(amount * 0.05, 10000);
                } else if (category.equals("hospital")) {
                    discount = 0;
                } else if (category.equals("pharmacy")) {
                    discount = 0;
                }
                // 추가 계산식 작성 필요
                break;
            case 4:
                if(amount >=0 && amount <30000)
                    discount = amount * 0.005;
                else if(amount >=30000 && amount <100000)
                    discount = amount * 0.007;
                else if(amount >=100000 && amount <300000)
                    discount = amount * 0.01;
                else if(amount >=300000 && amount <1000000)
                    discount = amount * 0.02;
                else if(amount >=1000000)
                    discount = amount * 0.03;
                break;
//              ---------------------------국민------------------------------------
            case 5:
                if (category.equals("commercial_restaurant")) {
                    discount = amount * 0.10;
                } else if (category.equals("fastfood_restaurant")) {
                    discount = amount * 0.010;
                } else if (category.equals("convenience_store")) {
                    discount = amount * 0.10;
                } else if (category.equals("cu")) {
                    discount = amount * 0.10;
                } else if (category.equals("cafe")) {
                    discount = 0;
                } else if (category.equals("starbucks")) {
                    discount = 0;
                } else if (category.equals("supermarket")) {
                    discount = 0;
                } else if (category.equals("hospital")) {
                    discount = 0;
                } else if (category.equals("pharmacy")) {
                    discount = 0;
                }
                break;
            case 6:
                if (category.equals("commercial_restaurant")) {
                    discount = 0;
                } else if (category.equals("fastfood_restaurant")) {
                    discount = Math.min(amount * 0.2, 5000);
                } else if (category.equals("convenience_store")) {
                    discount = 0;
                } else if (category.equals("cu")) {
                    discount = 0;
                } else if (category.equals("cafe")) {
                    discount = Math.min(amount * 0.3, 5000);
                } else if (category.equals("starbucks")) {
                    discount = Math.min(amount * 0.3, 5000);
                } else if (category.equals("supermarket")) {
                    discount = 0;
                } else if (category.equals("hospital")) {
                    discount = 0;
                } else if (category.equals("pharmacy")) {
                    discount = 0;
                }
                break;
            case 7:
                if (category.equals("commercial_restaurant")) {
                    discount = Math.min(amount * 0.05, 5000);
                } else if (category.equals("fastfood_restaurant")) {
                    discount = 0;
                } else if (category.equals("convenience_store")) {
                    discount = Math.min(amount * 0.05, 5000);
                } else if (category.equals("cu")) {
                    discount = Math.min(amount * 0.05, 5000);
                } else if (category.equals("cafe")) {
                    discount = Math.min(amount * 0.05, 5000);
                } else if (category.equals("starbucks")) {
                    discount = Math.min(amount * 0.05, 5000);
                } else if (category.equals("supermarket")) {
                    discount = Math.min(amount * 0.1, 5000);
                } else if (category.equals("hospital")) {
                    discount = Math.min(amount * 0.05, 5000);
                } else if (category.equals("pharmacy")) {
                    discount = Math.min(amount * 0.05, 5000);
                }
                break;
            case 8:
                if (category.equals("commercial_restaurant")) {
                    discount = 0;
                } else if (category.equals("fastfood_restaurant")) {
                    if(amount >=10000)
                        discount = amount * 0.05;
                    else
                        discount = 0;
                } else if (category.equals("convenience_store")) {
                    if(amount >=10000)
                        discount = amount * 0.05;
                    else
                        discount = 0;
                } else if (category.equals("cu")) {
                    if(amount >=10000)
                        discount = amount * 0.05;
                    else
                        discount = 0;
                } else if (category.equals("cafe")) {
                    if(amount >=10000)
                        discount = amount * 0.05;
                    else
                        discount = 0;
                } else if (category.equals("starbucks")) {
                    if(amount >=10000)
                        discount = amount * 0.05;
                    else
                        discount = 0;
                } else if (category.equals("supermarket")) {
                    discount = 0;
                } else if (category.equals("hospital")) {
                    discount = 0;
                } else if (category.equals("pharmacy")) {
                    if(amount >=10000)
                        discount = amount * 0.05;
                    else
                        discount = 0;
                }
                break;
            //              ---------------------------tkatjd------------------------------------
            case 9:
                if (category.equals("commercial_restaurant")) {
                    if(amount >=10000)
                        discount = 1000;
                    else
                        discount = 0;
                } else if (category.equals("fastfood_restaurant")) {
                    discount = 0;
                } else if (category.equals("convenience_store")) {
                    discount = amount * 0.15;
                } else if (category.equals("cu")) {
                    discount = amount * 0.15;
                } else if (category.equals("cafe")) {
                    discount = 0;
                } else if (category.equals("starbucks")) {
                    discount = 0;
                } else if (category.equals("supermarket")) {
                    discount = 0;
                } else if (category.equals("hospital")) {
                    discount = 0;
                } else if (category.equals("pharmacy")) {
                    discount = 0;
                }
                break;
            case 10:
                if(amount >=100000)
                    discount = amount * 0.01;
                else
                    discount = amount * 0.007;
                break;
            case 11:
                if (category.equals("commercial_restaurant")) {
                    discount = 0;
                } else if (category.equals("fastfood_restaurant")) {
                    discount = 0;
                } else if (category.equals("convenience_store")) {
                    discount = 0;
                } else if (category.equals("cu")) {
                    discount = 0;
                } else if (category.equals("cafe")) {
                    discount = 0;
                } else if (category.equals("starbucks")) {
                    discount = 0;
                } else if (category.equals("supermarket")) {
                    discount = amount * 0.07;
                } else if (category.equals("hospital")) {
                    discount = 0;
                } else if (category.equals("pharmacy")) {
                    discount = 0;
                }
                break;
            case 12:
                if (category.equals("commercial_restaurant")) {
                    discount = 0;
                } else if (category.equals("fastfood_restaurant")) {
                    discount = 0;
                } else if (category.equals("convenience_store")) {
                    discount = amount * 0.1;
                } else if (category.equals("cu")) {
                    discount = amount * 0.1;
                } else if (category.equals("cafe")) {
                    discount = amount * 0.1;
                } else if (category.equals("starbucks")) {
                    discount = amount * 0.1;
                } else if (category.equals("supermarket")) {
                    discount = 0;
                } else if (category.equals("hospital")) {
                    discount = 0;
                } else if (category.equals("pharmacy")) {
                    discount = 0;
                }
                break;
            //              ---------------------------BC------------------------------------
            case 13:
                if (category.equals("commercial_restaurant")) {
                    discount = amount * 0.07;
                } else if (category.equals("fastfood_restaurant")) {
                    discount = 0;
                } else if (category.equals("convenience_store")) {
                    discount = amount * 0.1;
                } else if (category.equals("cu")) {
                    discount = amount * 0.1;
                } else if (category.equals("cafe")) {
                    discount = amount * 0.1;
                } else if (category.equals("starbucks")) {
                    discount = amount * 0.1;
                } else if (category.equals("supermarket")) {
                    discount = 0;
                } else if (category.equals("hospital")) {
                    discount = 0;
                } else if (category.equals("pharmacy")) {
                    discount = 0;
                }
                break;
            case 14:
                if (category.equals("commercial_restaurant")) {
                    discount = 0;
                } else if (category.equals("fastfood_restaurant")) {
                    discount = 0;
                } else if (category.equals("convenience_store")) {
                    if(amount >=1800 && amount <18000)
                        discount = 180;
                    else if(amount >=18000)
                        discount = 1800;
                    else
                        discount = 0;
                } else if (category.equals("cu")) {
                    if(amount >=1800 && amount <18000)
                        discount = 180;
                    else if(amount >=18000)
                        discount = 1800;
                    else
                        discount = 0;
                } else if (category.equals("cafe")) {
                    if(amount >=1800 && amount <18000)
                        discount = 180;
                    else if(amount >=18000)
                        discount = 1800;
                    else
                        discount = 0;
                } else if (category.equals("starbucks")) {
                    if(amount >=1800 && amount <18000)
                        discount = 180;
                    else if(amount >=18000)
                        discount = 1800;
                    else
                        discount = 0;
                } else if (category.equals("supermarket")) {
                    discount = 0;
                } else if (category.equals("hospital")) {
                    discount = 0;
                } else if (category.equals("pharmacy")) {
                    discount = 0;
                }
                break;
            case 15:
                if (category.equals("commercial_restaurant")) {
                    discount = Math.min(amount * 0.07, 5000);
                } else if (category.equals("fastfood_restaurant")) {
                    discount = 0;
                } else if (category.equals("convenience_store")) {
                    discount = amount * 0.007;
                } else if (category.equals("cu")) {
                    discount = amount * 0.007;
                } else if (category.equals("cafe")) {
                    discount = amount * 0.007;
                } else if (category.equals("starbucks")) {
                    discount = amount * 0.007;
                } else if (category.equals("supermarket")) {
                    discount = amount * 0.007;
                } else if (category.equals("hospital")) {
                    discount = amount * 0.007;
                } else if (category.equals("pharmacy")) {
                    discount = amount * 0.007;
                }
                break;
            case 16:
                if (category.equals("commercial_restaurant")) {
                    discount = 0;
                } else if (category.equals("fastfood_restaurant")) {
                    discount = 0;
                } else if (category.equals("convenience_store")) {
                    discount = Math.min(amount * 0.07, 5000);
                } else if (category.equals("cu")) {
                    discount = Math.min(amount * 0.07, 5000);
                } else if (category.equals("cafe")) {
                    discount = Math.min(amount * 0.07, 5000);
                } else if (category.equals("starbucks")) {
                    discount = Math.min(amount * 0.07, 5000);
                } else if (category.equals("supermarket")) {
                    discount = 0;
                } else if (category.equals("hospital")) {
                    discount = Math.min(amount * 0.07, 5000);
                } else if (category.equals("pharmacy")) {
                    discount = Math.min(amount * 0.07, 5000);
                }
                break;
            default:
                break;
        }

        return discount;
    }

    // 카드 추천 함수
    public static Map<String, Object> recommendCard(String category, double amount) {
        int bestCard = -1;
        double bestDiscount = 0;
        double finalAmount = amount;

        int[] cardTypes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}; // 카드 번호 리스트
        for (int cardType : cardTypes) {
            double discount = calculateDiscount(cardType, category, amount);

            if (discount > bestDiscount) {
                bestDiscount = discount;
                bestCard = cardType;
                finalAmount = amount - discount; // 최종 결제 금액 계산
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("bestCard", bestCard);            //DB의 카드 번호ex)1,2,3,4...
        result.put("bestDiscount", bestDiscount);    // 할닝 금액
        result.put("finalAmount", finalAmount);      // 최종 결제 금액

        return result;
    }

    public static void main(String[] args) {
        // 카테고리와 금액 입력
        String category = "commercial_restaurant";  // 여기에 카테고리 인자 받아오면 되고
        double amount = 100000;  // 여기서 결제 금액 인자로 받아서 넣고

        // 카드 추천
        Map<String, Object> recommendation = recommendCard(category, amount);
        int bestCard = (int) recommendation.get("bestCard");
        double bestDiscount = (double) recommendation.get("bestDiscount");
        double finalAmount = (double) recommendation.get("finalAmount");

        if (bestCard == -1) {
            System.out.println("해당 카테고리에 대해 할인을 제공하는 카드가 없습니다.");
        } else {
            System.out.println("추천 카드 번호: " + bestCard);
            System.out.println("할인 금액: " + bestDiscount + "원");
            System.out.println("할인 적용 후 결제 금액: " + finalAmount + "원");
        }
    }
}

