## python setting for macOS

> macOS Catalina(10.15) 기준으로 작성되었습니다. 이전 버전의 macOS일 경우, 사소한 차이가 있을 수 있으나 기본적인 설정 및 설치 방법은 같습니다.

> **[시작하기전 주의사항]**
>
> macOS에서 기본 shell은 zsh로 변경되었습니다. [참고기사](https://www.theverge.com/2019/6/4/18651872/apple-macos-catalina-zsh-bash-shell-replacement-features)
>
> 만약 아직 bash를 사용중이라면 아래 명령어를 입력 후 shell을 재시작해서 zsh로 변경되었는지 확인합니다.
>
> ```bash
> $ chsh -s /bin/zsh
> ```

### homebrew

> https://brew.sh/index_ko

1. install

   ```bash
   $ /usr/bin/ruby -e "$(curl -fsSL https://raw.11githubusercontent.com/Homebrew/install/master/install)"
   ```



### pyenv

> https://github.com/pyenv/pyenv

1. install

   ```bash
   $ brew install pyenv
   $ echo -e 'if command -v pyenv 1>/dev/null 2>&1; then\n  eval "$(pyenv init -)"\nfi' >> ~/.zshrc
   $ exec "$SHELL"
   $ pyenv install 3.7.6
   $ pyenv global 3.7.6
   $ pyenv rehash
   $ source ~/.zshrc
   ```



### pyenv-virtualenv

> https://github.com/pyenv/pyenv-virtualenv

1. install

   ```bash
   $ brew install pyenv-virtualenv
   $ echo 'eval "$(pyenv virtualenv-init -)"' >> ~/.zshrc
   $ exec "$SHELL"
   $ source ~/.zshrc
   
   # optional, but recommended (by pyenv doc)
   $ brew install openssl readline sqlite3 xz zlib
   ```



---



### how to use pyenv

```bash
# 설치 가능한 목록 확인
$ pyenv install --list


# 가상환경 만들기
# pyenv virtualenv (버전) (가상환경이름)
$ pyenv virtualenv 3.7.6 my-virtual-env


# 가상환경 활성화 (가상환경을 켜고 싶은 위치에서 진행)
# 비활성화는 pyenv deactivate 가 있으나 해당 환경(폴더)에서 나가면 자동으로 비활성화됨
$ pyenv local my-virtual-env


# 가상환경 삭제
$ pyenv uninstall my-virtual-env


# 현재 만들어진 가상환경 목록 확인
$ pyenv virtualenvs
```

