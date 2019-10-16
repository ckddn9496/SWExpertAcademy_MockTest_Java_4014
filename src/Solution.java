import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

class Solution {
	static HashMap<String, Integer> lines = new HashMap<>();

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());

			lines.clear();

			int map[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				st = new StringTokenizer(s);
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
				s = s.replace(" ", "").trim();
				String reverse = new StringBuilder(s).reverse().toString();
				if (lines.containsKey(s)) {
					lines.put(s, lines.get(s) + 1);
				} else if (lines.containsKey(reverse)) {
					lines.put(reverse, lines.get(reverse) + 1);
				} else {
					lines.put(s, 1);
				}
			}

			for (int i = 0; i < N; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < N; j++) {
					sb.append(map[j][i]);
				}
				String reverse = sb.reverse().toString();
				if (lines.containsKey(sb.toString())) {
					lines.put(sb.toString(), lines.get(sb.toString()) + 1);
				} else if (lines.containsKey(reverse)) {
					lines.put(reverse, lines.get(reverse) + 1);
				} else {
					lines.put(sb.toString(), 1);
				}
			}

			int total = 0;
			for (Iterator<String> it = lines.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				char prev = 0;
				int count = 0;
				char cur = key.charAt(0);
				boolean flag = true;

				for (int j = 0; j < N; j++) {
					prev = cur;
					cur = key.charAt(j);
					if (prev == cur) {
						count++;
						if (flag == false && count >= X) {
							flag = true;
							count = 0;
						}
					} else if (prev + 1 == cur) {
						if (count >= X) {
							flag = true;
							count = 1;
						} else {
							flag = false;
							break;
						}
					} else if (prev == cur + 1) {
						if (flag == false) {
							break;
						} else {
							count = 1;
							flag = false;
						}
					} else {
						flag = false;
						break;
					}

				}

				if (flag) {
					total += lines.get(key);
				}
			}

			System.out.println("#" + test_case + " " + total);

		}
	}
}